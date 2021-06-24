package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class ETCApiClient(private val apiService: ETCApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { etcResponse -> etcResponse.balance }
                .map { WalletBalance(it) }
    }
}
