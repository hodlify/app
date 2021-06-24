package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import ru.nikitazhelonkin.coinbalance.data.api.response.YoBitBalanceResponse

interface YoBitApiService {

    @POST("tapi")
    @FormUrlEncoded
    fun request(
            @Field("method") method: String,
            @Field("nonce") nonce: String,
            @Header("Key") apiKey: String,
            @Header("Sign") signature: String): Single<YoBitBalanceResponse>
}
