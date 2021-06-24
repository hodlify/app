package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class XRPApiClient(private val xrpApiService: XRPApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return xrpApiService.balance(address)
                .map { response -> response.getBalance("XRP")?.balance ?: "0" }
                .map { WalletBalance(it) }
    }
}
