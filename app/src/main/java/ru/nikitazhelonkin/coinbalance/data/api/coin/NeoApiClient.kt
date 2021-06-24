package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.response.NeoScanBalanceResponse
import ru.nikitazhelonkin.coinbalance.data.entity.Token
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance
import java.util.*

class NeoApiClient(private val apiService: NeoScanApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address)
                .map { mapToWalletBalance(it) }
    }

    private fun mapToWalletBalance(response: NeoScanBalanceResponse): WalletBalance {
        var walletBalance = "0"
        val tokens = ArrayList<Token>()
        for (b in response.balances) {
            if ("NEO".equals(b.assetSymbol, ignoreCase = true)) {
                walletBalance = b.amount.toString()
            } else {
                tokens.add(Token(0, response.address, b.assetSymbol, b.asset, b.amount))
            }
        }
        return WalletBalance(walletBalance, tokens)
    }

}
