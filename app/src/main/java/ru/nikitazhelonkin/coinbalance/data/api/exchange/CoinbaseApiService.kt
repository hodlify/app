package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ru.nikitazhelonkin.coinbalance.data.api.response.CoinbaseBalanceResponse
import ru.nikitazhelonkin.coinbalance.data.api.response.CoinbaseTimeResponse

interface CoinbaseApiService {

    @get:GET("/v2/time")
    val time: Single<CoinbaseTimeResponse>

    @GET("/v2/accounts")
    fun balances(@Header("CB-ACCESS-KEY") apiKey: String,
                 @Header("CB-ACCESS-SIGN") signature: String,
                 @Header("CB-ACCESS-TIMESTAMP") timestamp: String): Single<CoinbaseBalanceResponse>
}
