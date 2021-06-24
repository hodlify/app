package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class BCHApiClient(private val apiService: BCHChainApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { bchChainResponse -> convert(bchChainResponse.data.balance) }
                .map { WalletBalance(it) }
    }

    private fun convert(s: String): String {
        return (s.toDouble() / 100000000.0).toString()
    }
}
