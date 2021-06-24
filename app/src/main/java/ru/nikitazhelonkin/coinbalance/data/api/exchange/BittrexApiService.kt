package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.BittrexBalancesResponse

interface BittrexApiService {

    @GET("api/v1.1/account/getbalances")
    fun balances(@Query("apikey") apiKey: String,
                 @Query("nonce") nonce: String,
                 @Header("apisign") signature: String): Single<BittrexBalancesResponse>
}
