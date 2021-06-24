package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.data.exception.ApiError
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil
import java.util.*

class BittrexApiClient(private val apiService: BittrexApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        val nonce = System.currentTimeMillis().toString()
        val url = String.format("https://bittrex.com/api/v1.1/account/getbalances?apikey=%s&nonce=%s", apiKey, nonce)
        val signature = DigestUtil.hmacString(url, apiSecret, "HmacSHA512")
        return apiService.balances(apiKey, nonce, signature)
                .map { response ->
                    if (response.isSuccess) {
                        response.nonZeroBalances
                    } else {
                        throw ApiError(response.message)
                    }
                }
                .compose(HttpErrorTransformer())
    }
}
