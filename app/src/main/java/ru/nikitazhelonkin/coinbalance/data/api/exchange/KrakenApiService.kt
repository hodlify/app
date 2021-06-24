package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import ru.nikitazhelonkin.coinbalance.data.api.response.KrakenBalancesResponse

interface KrakenApiService {

    @POST("0/private/Balance")
    @FormUrlEncoded
    fun getBalances(@Field("nonce") nonce: String,
                    @Header("API-Key") apiKey: String,
                    @Header("API-Sign") apiSign: String): Single<KrakenBalancesResponse>
}
