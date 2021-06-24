package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.ETCResponse

interface ETCApiService {

    @GET("api/v1/getAddressBalance")
    fun balance(@Query("address") address: String): Single<ETCResponse>
}
