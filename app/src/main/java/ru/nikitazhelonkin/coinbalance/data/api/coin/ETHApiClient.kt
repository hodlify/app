package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.response.EthplorerApiResponse
import ru.nikitazhelonkin.coinbalance.data.entity.Token
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance

class ETHApiClient(private val apiService: EthplorerApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address, "freekey")
                .map { ethResponse -> WalletBalance(ethResponse.ETH.balance, mapTokenList(address, ethResponse.tokens)) }
    }

    private fun mapTokenList(address: String, tokenList: List<EthplorerApiResponse.Token>): List<Token> {
        return tokenList.map { t -> Token(0, address, t.tokenInfo.symbol, t.tokenInfo.name, formatBalance(t.balance, t.tokenInfo.decimals)) }
    }

    private fun formatBalance(balance: String, decimals: Int): Float {
        return (balance.toDouble() / Math.pow(10.0, decimals.toDouble())).toFloat()
    }

}
