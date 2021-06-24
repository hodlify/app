package ru.nikitazhelonkin.coinbalance.data.api.exchange


import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.POST
import ru.nikitazhelonkin.coinbalance.data.api.response.GeminiBalanceResponse

interface GeminiApiService {

    @POST("v1/balances")
    fun balances(@Header("X-GEMINI-APIKEY") apiKey: String,
                 @Header("X-GEMINI-PAYLOAD") payload: String,
                 @Header("X-GEMINI-SIGNATURE") signature: String): Single<GeminiBalanceResponse>
}
