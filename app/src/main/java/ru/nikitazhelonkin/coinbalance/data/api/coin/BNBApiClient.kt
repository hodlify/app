package ru.nikitazhelonkin.coinbalance.data.api.coin

import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance
import java.math.BigDecimal


class BNBApiClient(private val apiService: BNBApiService) : ApiClient {

    override fun getBalance(address: String): Single<WalletBalance> {
        return apiService.balance(address, API_KEY).map {
            if("OK" == it.message){
                WalletBalance(convert(it.result.toString()))
            }else{
                WalletBalance("Error")
            }
        }
    }

    private fun convert(s: String): String {
        val result = BigDecimal(s).divide(BigDecimal("1000000000000000000"))
        return result.toPlainString()
    }

    companion object{
        private const val API_KEY = "1GA66MHPKGJC9QPGEAE9WB7BFZG8SUA81K"
    }
}