package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.ChainsoResponse

interface ChainsoApiService {

    @GET("api/v2/get_address_balance/{ticker}/{address}")
    fun balance(@Path("ticker") ticker: String,
                @Path("address") address: String): Single<ChainsoResponse>
}
