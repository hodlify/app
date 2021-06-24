package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.AdaBalanceResponse

interface AdaApiService {

    @GET("api/addresses/summary/{address}")
    fun balance(@Path("address") address: String): Single<AdaBalanceResponse>
}
