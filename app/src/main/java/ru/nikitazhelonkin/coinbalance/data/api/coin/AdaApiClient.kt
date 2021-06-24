package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class AdaApiClient(private val apiService: AdaApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { adaBalanceResponse -> convert(adaBalanceResponse.data.balance.value) }
                .map { WalletBalance(it) }
    }

    private fun convert(s: String): String {
        return (s.toDouble() / 1000000.0).toString()
    }
}
