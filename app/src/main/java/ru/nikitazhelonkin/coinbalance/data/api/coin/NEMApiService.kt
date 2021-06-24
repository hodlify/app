package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.NEMResponse

interface NEMApiService {

    @GET("api3/account_net")
    fun balance(@Query("address") address: String): Single<NEMResponse>

}
