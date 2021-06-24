package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class LTCApiClient(private val chainzApiService: ChainzApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return chainzApiService.balance("ltc", address)
                .map { it.string() }
                .map { WalletBalance(it) }
    }

}
