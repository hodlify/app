package ru.nikitazhelonkin.coinbalance.data.api.exchange


import java.util.HashMap

import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.data.api.response.CoinbaseBalanceResponse
import ru.nikitazhelonkin.coinbalance.data.api.exchange.CoinbaseApiService
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil

class CoinbaseApiClient(private val mApiService: CoinbaseApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        return mApiService.time.flatMap { r ->
            val timestamp = r.data.epoch
            val payload = timestamp + "GET" + "/v2/accounts"
            val signature = DigestUtil.hmacString(payload, apiSecret, "HmacSHA256")
            mApiService.balances(apiKey, signature, timestamp)
                    .map{ it.nonZeroBalances }
                    .compose(HttpErrorTransformer())
        }

    }

}
