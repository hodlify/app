package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import okhttp3.Credentials
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import java.util.*

class HitBTCApiClient(private val mApiService: HitBTCApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        return mApiService.balances(Credentials.basic(apiKey, apiSecret))
                .map { it.nonZeroBalances }
                .compose(HttpErrorTransformer())
    }
}
