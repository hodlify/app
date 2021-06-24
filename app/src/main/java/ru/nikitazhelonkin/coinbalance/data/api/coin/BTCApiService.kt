package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface BTCApiService {

    @GET("q/addressbalance/{address}")
    fun balance(@Path("address") address: String): Single<ResponseBody>

}
