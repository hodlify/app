package ru.nikitazhelonkin.coinbalance.data.api.coin

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.BNBBalanceResponse


interface BNBApiService {

    @GET("api?module=account&action=balance&tag=latest")
    fun balance(@Query("address") address: String, @Query("apikey") apiKey: String): Single<BNBBalanceResponse>
}
