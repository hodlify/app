package ru.nikitazhelonkin.coinbalance.data.api.coin

import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.response.DotBalanceRequest
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance


class DotApiClient(private val apiService: DotApiService) : ApiClient {
    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(DotBalanceRequest(address)).map {
            WalletBalance(it.data.account.balance)
        }
    }

}