package ru.nikitazhelonkin.coinbalance.data.api.exchange


import android.util.Base64
import io.reactivex.Single
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer
import ru.nikitazhelonkin.coinbalance.data.exception.ApiError
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil
import java.nio.charset.Charset
import java.util.*

class KrakenApiClient(private val mApiService: KrakenApiService) : ExchangeApiClient {

    override fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>> {
        val nonce = System.currentTimeMillis().toString()
        val decodedSecret = Base64.decode(apiSecret, Base64.DEFAULT)
        val pathBytes = "/0/private/Balance".toByteArray(Charset.forName("UTF-8"))
        val hash256Bytes = DigestUtil.sha256(nonce + "nonce=" + nonce)
        val signature = DigestUtil.hmac(concat(pathBytes, hash256Bytes!!), decodedSecret, "HmacSHA512")
        val signatureEncoded = Base64.encodeToString(signature, Base64.NO_WRAP)
        return mApiService.getBalances(nonce, apiKey, signatureEncoded)
                .map { response ->
                    if (response.error != null && response.error.isNotEmpty()) {
                        throw ApiError(Arrays.toString(response.error))
                    } else {
                        response.balances
                    }
                }
                .compose(HttpErrorTransformer())
    }

    private fun concat(a: ByteArray, b: ByteArray): ByteArray {
        val c = ByteArray(a.size + b.size)
        System.arraycopy(a, 0, c, 0, a.size)
        System.arraycopy(b, 0, c, a.size, b.size)
        return c
    }

}
