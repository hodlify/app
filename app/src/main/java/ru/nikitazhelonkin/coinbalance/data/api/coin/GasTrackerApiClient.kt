package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class GasTrackerApiClient(private val apiService: GasTrackerApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { response -> response.balance.ether }
                .map { WalletBalance(it) }
    }
}
