package ru.nikitazhelonkin.coinbalance.data.api.exchange


import android.util.Base64
import io.reactivex.Single
import org.json.JSONException
import org.json.JSONObject
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil
import java.util.*

class GeminiApiClient(private val mApiService: GeminiApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        val payload: String
        try {
            val jo = JSONObject()
            jo.put("nonce", System.currentTimeMillis().toString())
            jo.put("request", "/v1/balances")
            payload = jo.toString()
        } catch (e: JSONException) {
            return Single.error(e)
        }

        val payloadBase64 = Base64.encodeToString(payload.toByteArray(), Base64.NO_WRAP)
        val signature = DigestUtil.hmacString(payloadBase64, apiSecret, "HmacSHA384")
        return mApiService.balances(apiKey, payloadBase64, signature)
                .map { it.nonZeroBalances }
                .compose(HttpErrorTransformer())
    }
}
