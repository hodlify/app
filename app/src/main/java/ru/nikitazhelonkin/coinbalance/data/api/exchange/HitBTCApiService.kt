package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ru.nikitazhelonkin.coinbalance.data.api.response.HitBTCBalancesResponse

interface HitBTCApiService {

    @GET("/api/2/account/balance")
    fun balances(@Header("Authorization") authorization: String): Single<HitBTCBalancesResponse>
}
