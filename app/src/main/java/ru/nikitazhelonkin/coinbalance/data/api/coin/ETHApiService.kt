package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.EthResponse

interface ETHApiService {

    @GET("api?module=account&action=balance")
    fun balance(@Query("address") address: String): Single<EthResponse>
}
