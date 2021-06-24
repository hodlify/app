package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.NeoScanBalanceResponse

interface NeoScanApiService {

    @GET("api/main_net/v1/get_balance/{address}")
    fun balance(@Path("address") address: String): Single<NeoScanBalanceResponse>
}
