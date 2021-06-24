package ru.nikitazhelonkin.coinbalance.data.api.exchange


import java.util.HashMap

import io.reactivex.Single

interface ExchangeApiClient {

    fun getBalances(apiKey: String, apiSecret: String): Single<HashMap<String, Float>>
}
