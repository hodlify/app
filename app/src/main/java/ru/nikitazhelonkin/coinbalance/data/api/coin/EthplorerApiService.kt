package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.EthplorerApiResponse

interface EthplorerApiService {

    @GET("getAddressInfo/{address}")
    fun balance(
            @Path("address") address: String,
            @Query("apiKey") apiKey: String
    ): Single<EthplorerApiResponse>
}
