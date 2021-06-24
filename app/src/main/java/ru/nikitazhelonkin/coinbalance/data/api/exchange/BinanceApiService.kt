package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.BinanceBalancesResponse
import ru.nikitazhelonkin.coinbalance.data.api.response.BinanceTimeResponse

interface BinanceApiService {

    @GET("api/v3/account")
    fun balances(
            @Query("timestamp") timeStamp: String,
            @Query("signature") signature: String,
            @Header("X-MBX-APIKEY") apiKey: String): Single<BinanceBalancesResponse>

    @GET("api/v1/time")
    fun time(): Single<BinanceTimeResponse>
}
