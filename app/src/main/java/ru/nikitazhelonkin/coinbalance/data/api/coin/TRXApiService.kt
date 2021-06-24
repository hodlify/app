package ru.nikitazhelonkin.coinbalance.data.api.coin

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXAssetResponse
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXWalletResponse

interface TRXApiService {

    @GET("v1/accounts/{address}")
    fun balance(@Path("address") address: String): Single<TRXWalletResponse>

    @GET("v1/assets/{id}")
    fun getAsset(@Path("id") id: String): Single<TRXAssetResponse>
}
