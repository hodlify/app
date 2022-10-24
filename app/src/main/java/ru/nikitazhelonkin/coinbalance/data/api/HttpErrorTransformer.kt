package ru.nikitazhelonkin.coinbalance.data.api


import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.HttpException
import retrofit2.Response
import ru.nikitazhelonkin.coinbalance.data.exception.ApiError


class HttpErrorTransformer<T> : SingleTransformer<T, T> {

    override fun apply(single: Single<T>): SingleSource<T> {
        return single.onErrorResumeNext { throwable ->
            if (throwable is HttpException) {
                val response = throwable.response()
                if (response != null) {
                    response.errorBody().string()
                }
            }
            Single.error(throwable)
        }
    }
}