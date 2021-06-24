package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil
import java.util.*

class BinanceApiClient(private val apiService: BinanceApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        val time = apiService.time().blockingGet().serverTime
        val timeStamp = time.toString()
        val queryString = String.format("timestamp=%s", timeStamp)
        val signature = DigestUtil.hmacString(queryString, apiSecret, "HmacSHA256")
        return apiService.balances(timeStamp, signature, apiKey)
                .map { it.nonZeroBalances }
                .compose(HttpErrorTransformer())
    }
}
