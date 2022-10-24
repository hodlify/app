package ru.nikitazhelonkin.coinbalance.data.api.interceptor


import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

import okhttp3.Connection
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import ru.nikitazhelonkin.coinbalance.utils.L

class LoggingInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body()
        val hasRequestBody = requestBody != null

        val connection = chain.connection()
        val protocol = connection?.protocol() ?: Protocol.HTTP_1_1
        var requestStartMessage = "--> " + request.method() + ' '.toString() + requestPath(request.url()) + ' '.toString() + protocol(protocol)
        if (hasRequestBody) {
            requestStartMessage += " ($requestBody-byte body)"
        }
        log(requestStartMessage)

        if (hasRequestBody) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)

            val contentType = requestBody.contentType()
            val charset = contentType?.charset(UTF8) ?: UTF8
            log("")
            if (isPlaintext(buffer)) {
                log("BODY:" + buffer.readString(charset))
            }
        }

        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        val responseBody = response.body()
        log("<-- " + protocol(response.protocol()) + ' '.toString() + response.code() + ' '.toString()
                + response.message() + " (" + tookMs + "ms"
                + ", " + responseBody.contentLength() + "-byte body")
        var endMessage = "<-- END HTTP"
        val source = responseBody.source()
        source.request(Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source.buffer()

        val contentType = responseBody.contentType()
        val charset = contentType?.charset(UTF8) ?: UTF8

        if (responseBody.contentLength() != 0L && isPlaintext(buffer)) {
            log("")
            log("RESPONSE:" + buffer.clone().readString(charset))
        }

        endMessage += " (" + buffer.size() + "-byte body)"
        log(endMessage)
        return response
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")
        private val TAG = "OkHttp"

        private fun protocol(protocol: Protocol): String {
            return if (protocol == Protocol.HTTP_1_0) "HTTP/1.0" else "HTTP/1.1"
        }

        private fun requestPath(url: HttpUrl): String {
            val path = url.encodedPath()
            val query = url.encodedQuery()
            return if (query != null) "$path?$query" else path
        }

        @Throws(EOFException::class)
        internal fun isPlaintext(buffer: Buffer): Boolean {
            try {
                val prefix = Buffer()
                val byteCount = if (buffer.size() < 64) buffer.size() else 64
                buffer.copyTo(prefix, 0, byteCount)
                for (i in 0..15) {
                    if (prefix.exhausted()) {
                        break
                    }
                    if (Character.isISOControl(prefix.readUtf8CodePoint())) {
                        return false
                    }
                }
                return true
            } catch (e: EOFException) {
                return false // Truncated UTF-8 sequence.
            }

        }

        private fun log(message: String) {
            L.d(TAG, message)
        }
    }


}
