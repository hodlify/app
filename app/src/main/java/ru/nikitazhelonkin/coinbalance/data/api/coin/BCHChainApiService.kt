package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.BCHChainResponse

interface BCHChainApiService {

    @GET("v3/address/{address}")
    fun balance(@Path("address") address: String): Single<BCHChainResponse>
}
