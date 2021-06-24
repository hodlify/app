package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import ru.nikitazhelonkin.coinbalance.data.api.response.PoloniexBalancesResponse

interface PoloniexApiService {

    @POST("tradingApi")
    @FormUrlEncoded
    fun balances(
            @Field("nonce") nonce: String,
            @Field("command") command: String,
            @Header("Key") apiKey: String,
            @Header("Sign") apiSign: String): Single<PoloniexBalancesResponse>
}
