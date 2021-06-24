package ru.nikitazhelonkin.coinbalance.data.api.exchange


import java.util.HashMap

import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.data.api.response.PoloniexBalancesResponse
import ru.nikitazhelonkin.coinbalance.data.api.exchange.PoloniexApiService
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil

class PoloniexApiClient(private val mApiService: PoloniexApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        val nonce = System.currentTimeMillis().toString()
        val signature = DigestUtil.hmacString("nonce=$nonce&command=returnBalances", apiSecret, "HmacSHA512")
        return mApiService.balances(nonce, "returnBalances", apiKey, signature)
                .map { it.nonZeroBalances }
                .compose(HttpErrorTransformer())
    }
}
