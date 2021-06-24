package ru.nikitazhelonkin.coinbalance.data.api.coin


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.XLMResponse

interface XLMApiService {

    @GET("accounts/{address}")
    fun balance(@Path("address") address: String): Single<XLMResponse>
}
