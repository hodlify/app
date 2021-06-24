package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.response.DogeApiService
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class DogeApiClient(private val apiService: DogeApiService) : ApiClient {
    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { it.string() }
                .map { WalletBalance(it) }
    }
}
