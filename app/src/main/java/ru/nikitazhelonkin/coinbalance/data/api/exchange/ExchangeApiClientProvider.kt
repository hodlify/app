package ru.nikitazhelonkin.coinbalance.data.api.exchange


import javax.inject.Inject

import ru.nikitazhelonkin.coinbalance.data.entity.ExchangeService

class ExchangeApiClientProvider @Inject
constructor(private val bitfinexApiService: BitfinexApiService,
            private val bittrexApiService: BittrexApiService,
            private val binanceApiService: BinanceApiService,
            private val krakenApiService: KrakenApiService,
            private val mPoloniexApiService: PoloniexApiService,
            private val hitBTCApiService: HitBTCApiService,
            private val coinbaseApiService: CoinbaseApiService,
            private val geminiApiService: GeminiApiService,
            private val yoBitApiService: YoBitApiService,
            private val wexApiService: WexApiService) {

    fun provide(service: ExchangeService): ExchangeApiClient {
        return when (service) {
            ExchangeService.BITFINEX -> BitfinexApiClient(bitfinexApiService)
            ExchangeService.BITTREX -> BittrexApiClient(bittrexApiService)
            ExchangeService.BINANCE -> BinanceApiClient(binanceApiService)
            ExchangeService.KRAKEN -> KrakenApiClient(krakenApiService)
            ExchangeService.POLONIEX -> PoloniexApiClient(mPoloniexApiService)
            ExchangeService.HITBTC -> HitBTCApiClient(hitBTCApiService)
            ExchangeService.COINBASE -> CoinbaseApiClient(coinbaseApiService)
            ExchangeService.GEMINI -> GeminiApiClient(geminiApiService)
            ExchangeService.YOBIT -> YoBitApiClient(yoBitApiService)
            ExchangeService.WEX -> WexApiClient(wexApiService)
        }

    }
}
