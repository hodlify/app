package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

interface ApiClient {

    fun getBalance(address: String): Single<WalletBalance>
}
