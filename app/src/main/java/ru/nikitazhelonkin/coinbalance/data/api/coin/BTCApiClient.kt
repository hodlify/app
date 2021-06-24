package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class BTCApiClient(private val btcApiService: BTCApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return btcApiService.balance(address)
                .map { responseBody -> convert(responseBody.string()) }
                .map { WalletBalance(it) }
    }

    private fun convert(s: String): String {
        return (s.toDouble() / 100000000.0).toString()
    }
}
