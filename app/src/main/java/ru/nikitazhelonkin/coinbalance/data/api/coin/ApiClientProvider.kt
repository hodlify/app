package ru.nikitazhelonkin.coinbalance.data.api.coin


import ru.nikitazhelonkin.coinbalance.data.api.response.DogeApiService
import javax.inject.Inject

class ApiClientProvider @Inject constructor(
        private val btcApiService: BTCApiService,
        private val ethApiService: ETHApiService,
        private val ethplorerApiService: EthplorerApiService,
        private val chainsoApiService: ChainsoApiService,
        private val chainzApiService: ChainzApiService,
        private val xrpApiService: XRPApiService,
        private val bchChainApiService: BCHChainApiService,
        private val etcApiService: ETCApiService,
        private val gasTrackerApiService: GasTrackerApiService,
        private val dogeApiService: DogeApiService,
        private val nemApiService: NEMApiService,
        private val xlmApiService: XLMApiService,
        private val adaApiService: AdaApiService,
        private val neoScanApiService: NeoScanApiService,
        private val zChainApiService: ZChainApiService,
        private val trxApiService: TRXApiService,
        private val bnbApiService: BNBApiService,
        private val dotApiService: DotApiService
) {

    fun provide(coinTicker: String): ApiClient {
        return when (coinTicker) {
            "BTC" -> BTCApiClient(btcApiService)
            "ETH" -> ETHApiClient(ethplorerApiService)
            "LTC" -> LTCApiClient(chainzApiService)
            "XRP" -> XRPApiClient(xrpApiService)
            "DASH" -> DashApiClient(chainzApiService)
            "BCH" -> BCHApiClient(bchChainApiService)
            "ETC" -> GasTrackerApiClient(gasTrackerApiService)
            "DOGE" -> DogeApiClient(dogeApiService)
            "XEM" -> NEMApiClient(nemApiService)
            "XLM" -> XLMApiClient(xlmApiService)
            "ADA" -> AdaApiClient(adaApiService)
            "NEO" -> NeoApiClient(neoScanApiService)
            "ZEC" -> ZECApiClient(zChainApiService)
            "TRX" -> TRXApiClient(trxApiService)
            "BNB" -> BNBApiClient(bnbApiService)
            "DOT" -> DotApiClient(dotApiService)
            else -> throw IllegalArgumentException("Coin $coinTicker is unsupported")
        }

    }
}
