package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChainzApiService {

    @GET("{ticker}/api.dws?q=getbalance")
    fun balance(
            @Path("ticker") coin: String,
            @Query("a") address: String
    ): Single<ResponseBody>
}
