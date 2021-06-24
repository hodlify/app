package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.XrpResponse

interface XRPApiService {

    @GET("v2/accounts/{address}/balances")
    fun balance(@Path("address") address: String): Single<XrpResponse>
}
