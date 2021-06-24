package ru.nikitazhelonkin.coinbalance.data.api.service


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitazhelonkin.coinbalance.data.api.response.Prices

interface CryptoCompareApiService {

    @GET("data/pricemultifull")
    fun getPrices(@Query("fsyms") fsyms: String, @Query("tsyms") tsyms: String): Single<Prices>

}
