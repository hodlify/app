package ru.nikitazhelonkin.coinbalance.data.api.coin

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.nikitazhelonkin.coinbalance.data.api.response.DotBalanceRequest
import ru.nikitazhelonkin.coinbalance.data.api.response.DotBalanceResponse


interface DotApiService {

    @Headers(value = ["X-API-Key: 0df6e2e58b2aaf80a76c00946d98ef7a"])
    @POST("api/v2/scan/search")
    fun balance(@Body params: DotBalanceRequest): Single<DotBalanceResponse>
}