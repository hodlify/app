package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.nikitazhelonkin.coinbalance.data.api.response.BitfinexBalancesResponse

interface BitfinexApiService {


    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/v1/balances")
    fun balances(@Header("X-BFX-APIKEY") apiKey: String,
                 @Header("X-BFX-PAYLOAD") payload: String,
                 @Header("X-BFX-SIGNATURE") signature: String): Single<BitfinexBalancesResponse>
}
