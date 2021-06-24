package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.GasTrackerBalanceResponse

interface GasTrackerApiService {

    @GET("v1/addr/{address}")
    fun balance(@Path("address") address: String): Single<GasTrackerBalanceResponse>

}
