package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.data.api.response.WexBalanceResponse
import ru.nikitazhelonkin.coinbalance.data.exception.ApiError
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil
import java.util.*

class WexApiClient(private val mApiService: WexApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        return request("getInfo", apiKey, apiSecret)
                .map { response ->
                    if (response.isSuccess) {
                        response.nonZeroBalances
                    } else {
                        throw ApiError(response.error)
                    }
                }
    }

    private fun request(method: String, apiKey: String, apiSecret: String): Single<WexBalanceResponse> {
        val nonce = (System.currentTimeMillis() / 1000).toString()
        val payload = String.format("method=%s&nonce=%s", method, nonce)
        val signature = DigestUtil.hmacString(payload, apiSecret, "HmacSHA512")
        return mApiService.request(method, nonce, apiKey, signature)
                .compose(HttpErrorTransformer())
    }
}
