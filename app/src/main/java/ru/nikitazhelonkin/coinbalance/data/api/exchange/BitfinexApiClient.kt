package ru.nikitazhelonkin.coinbalance.data.api.exchange


import android.util.Base64
import io.reactivex.Single
import org.json.JSONException
import org.json.JSONObject
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil
import java.util.*

class BitfinexApiClient(private val apiService: BitfinexApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        val payload: String
        try {
            payload = JSONObject().apply {
                put("request", "/v1/balances")
                put("nonce", System.currentTimeMillis().toString())
            }.toString()
        } catch (e: JSONException) {
            return Single.error(e)
        }

        val payloadBase64 = Base64.encodeToString(payload.toByteArray(), Base64.NO_WRAP)
        val payloadSha384hmac = DigestUtil.hmacString(payloadBase64, apiSecret, "HmacSHA384")
        return apiService.balances(apiKey, payloadBase64, payloadSha384hmac)
                .map { it.nonZeroBalances }
                .compose(HttpErrorTransformer())
    }
}
