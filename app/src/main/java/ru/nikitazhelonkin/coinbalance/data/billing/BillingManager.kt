package ru.nikitazhelonkin.coinbalance.data.billing


import android.app.Activity
import com.android.billingclient.api.*
import ru.nikitazhelonkin.coinbalance.utils.L
import java.io.IOException
import java.util.*

class BillingManager(
        private val activity: Activity,
        private val base64PublicKey: String,
        private val billingUpdatesListener: BillingUpdatesListener
) : PurchasesUpdatedListener {

    private var billingClient: BillingClient? = BillingClient.newBuilder(activity).setListener(this).build()

    private val purchases = ArrayList<Purchase>()

    private var isServiceConnected: Boolean = false

    private var tokensToBeConsumed: MutableSet<String>? = null

    interface BillingUpdatesListener {

        fun onBillingClientSetupFinished()

        fun onConsumeFinished(token: String, @BillingClient.BillingResponse result: Int)

        fun onPurchasesUpdated(purchases: List<Purchase>)
    }


    fun setup() {
        startServiceConnection {
            billingUpdatesListener.onBillingClientSetupFinished()
            // IAB is fully set up. Now, let's get an inventory of stuff we own.
            queryPurchases()
        }
    }

    fun destroy() {
        L.e("Destroying the manager.")
        if (billingClient?.isReady == true) {
            billingClient?.endConnection()
            billingClient = null
        }
    }

    fun initiatePurchaseFlow(skuId: String, @BillingClient.SkuType billingType: String) {
        initiatePurchaseFlow(skuId, null, billingType)
    }

    fun initiatePurchaseFlow(skuId: String, oldSkus: ArrayList<String>?,
                             @BillingClient.SkuType billingType: String) {
        executeServiceRequest {
            L.e("Launching in-app purchase flow. Replace old SKU? " + (oldSkus != null))
            val purchaseParams = BillingFlowParams.newBuilder()
                    .setSku(skuId).setType(billingType).setOldSkus(oldSkus).build()
            billingClient?.launchBillingFlow(activity, purchaseParams)
        }
    }

    fun consumeAsync(purchaseToken: String) {
        // If we've already scheduled to consume this token - no action is needed (this could happen
        // if you received the token when querying purchases inside onReceive() and later from
        // onActivityResult()
        val toBeConsumed =  
        if (tokensToBeConsumed == null) {
            tokensToBeConsumed = HashSet()
        } else if (tokensToBeConsumed!!.contains(purchaseToken)) {
            L.e("Token was already scheduled to be consumed - skipping...")
            return
        }
        tokensToBeConsumed!!.add(purchaseToken)

        // Generating Consume Response listener
        val onConsumeListener = ConsumeResponseListener { responseCode, purchaseToken ->
            // If billing service was disconnected, we try to reconnect 1 time
            // (feel free to introduce your retry policy here).
            billingUpdatesListener.onConsumeFinished(purchaseToken, responseCode)
        }

        executeServiceRequest { billingClient!!.consumeAsync(purchaseToken, onConsumeListener) }
    }

    override fun onPurchasesUpdated(responseCode: Int, purchases: List<Purchase>?) {
        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
            billingUpdatesListener.onPurchasesUpdated(this.purchases)
        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
            L.e("onPurchasesUpdated() - user cancelled the purchase flow - skipping")
        } else {
            L.e("onPurchasesUpdated() got unknown resultCode: $responseCode")
        }
    }

    private fun handlePurchase(purchase: Purchase) {
        if (!verifyValidSignature(purchase.originalJson, purchase.signature)) {
            L.e("Got a purchase: $purchase; but signature is bad. Skipping...")
            return
        }

        L.e("Got a verified purchase: $purchase")

        purchases.add(purchase)
    }

    fun areSubscriptionsSupported(): Boolean {
        val responseCode = billingClient!!.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS)
        if (responseCode != BillingClient.BillingResponse.OK) {
            L.e("areSubscriptionsSupported() got an error response: $responseCode")
        }
        return responseCode == BillingClient.BillingResponse.OK
    }

    fun queryPurchases() {
        executeServiceRequest {
            L.e("Querying inventory.")
            val purchasesResult = billingClient!!.queryPurchases(BillingClient.SkuType.INAPP)
            // If there are subscriptions supported, we add subscription rows as well
            if (areSubscriptionsSupported()) {
                val subscriptionResult = billingClient!!.queryPurchases(BillingClient.SkuType.SUBS)
                L.e("Querying subscriptions result code: "
                        + subscriptionResult.responseCode
                        + " res: " + subscriptionResult.purchasesList.size)

                if (subscriptionResult.responseCode == BillingClient.BillingResponse.OK) {
                    purchasesResult.purchasesList.addAll(
                            subscriptionResult.purchasesList)
                } else {
                    L.e("Got an error response trying to query subscription purchases")
                }
            } else if (purchasesResult.responseCode == BillingClient.BillingResponse.OK) {
                L.e("Skipped subscription purchases query since they are not supported")
            } else {
                L.e("queryPurchases() got an error response code: " + purchasesResult.responseCode)
            }
            onQueryPurchasesFinished(purchasesResult)
        }
    }

    private fun onQueryPurchasesFinished(result: Purchase.PurchasesResult) {
        // Have we been disposed of in the meantime? If so, or bad result code, then quit
        if (billingClient == null || result.responseCode != BillingClient.BillingResponse.OK) {
            L.e("Billing client was null or result code (" + result.responseCode
                    + ") was bad - quitting")
            return
        }

        L.e("Query inventory was successful.")

        // Update the UI and purchases inventory with new list of purchases
        purchases.clear()
        onPurchasesUpdated(BillingClient.BillingResponse.OK, result.purchasesList)
    }

    private fun executeServiceRequest(runnable: () -> Unit) {
        if (isServiceConnected) {
            runnable.run()
        } else {
            // If billing service was disconnected, we try to reconnect 1 time.
            // (feel free to introduce your retry policy here).
            startServiceConnection(runnable)
        }
    }

    private fun startServiceConnection(executeOnSuccess: () -> Unit) {
        L.e("Starting setup.")
        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(@BillingClient.BillingResponse billingResponseCode: Int) {
                L.e("Setup successful.")
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    isServiceConnected = true
                    executeOnSuccess.invoke()
                }
            }

            override fun onBillingServiceDisconnected() {
                isServiceConnected = false
            }
        })
    }

    private fun verifyValidSignature(signedData: String, signature: String): Boolean {
        try {
            return Security.verifyPurchase(base64PublicKey, signedData, signature)
        } catch (e: IOException) {
            L.e("Got an exception trying to validate a purchase: $e")
            return false
        }

    }
}
