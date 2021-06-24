package ru.nikitazhelonkin.coinbalance.data.api.coin

import io.reactivex.Observable
import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXWalletResponse
import ru.nikitazhelonkin.coinbalance.data.entity.Token
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance
import kotlin.math.pow

class TRXApiClient(private val apiService: TRXApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { trxResponse -> WalletBalance(trxResponse.balance, getTokens(address, trxResponse)) }
    }

    private fun getTokens(address: String, trxResponse: TRXWalletResponse): List<Token> {
        return Observable.fromIterable(trxResponse.assets)
                .map { asset ->
                    val response = apiService.getAsset(asset.key).blockingGet()
                    val assetInfo = response.data[0]
                    val balance = (asset.value.toLong() / 10.0.pow(assetInfo.precision.toDouble())).toFloat()
                    Token(0, address, assetInfo.abbr, assetInfo.name, balance)
                }
                .toList()
                .blockingGet()
    }


}
