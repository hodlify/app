package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.ZChainBalanceResponse

interface ZChainApiService {

    @GET("v2/mainnet/accounts/{address}")
    fun balance(@Path("address") address: String): Single<ZChainBalanceResponse>

}
