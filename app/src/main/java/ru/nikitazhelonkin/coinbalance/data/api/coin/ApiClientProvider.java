package ru.nikitazhelonkin.coinbalance.data.api.coin;


import javax.inject.Inject;

import ru.nikitazhelonkin.coinbalance.data.api.response.DogeApiService;

public class ApiClientProvider {


    private BTCApiService mBTCApiService;
    private ETHApiService mETHApiService;
    private EthplorerApiService mEthplorerApiService;
    private ChainsoApiService mChainsoApiService;
    private ChainzApiService mChainzApiService;
    private XRPApiService mXRPApiService;
    private BCHChainApiService mBCHChainApiService;
    private ETCApiService mETCApiService;
    private GasTrackerApiService mGasTrackerApiService;
    private DogeApiService mDogeApiService;
    private NEMApiService mNEMApiService;
    private XLMApiService mXLMApiService;
    private AdaApiService mAdaApiService;
    private NeoScanApiService mNeoScanApiService;
    private ZChainApiService mZChainApiService;
    private TRXApiService mTRXApiService;
    private BNBApiService mBNBApiService;
    private DotApiService mDotApiService;

    @Inject
    public ApiClientProvider(BTCApiService btcApiService,
                             ETHApiService ethApiService,
                             EthplorerApiService ethplorerApiService,
                             ChainsoApiService chainsoApiService,
                             ChainzApiService chainzApiService,
                             XRPApiService xrpApiService,
                             BCHChainApiService bchChainApiService,
                             ETCApiService etcApiService,
                             GasTrackerApiService gasTrackerApiService,
                             DogeApiService dogeApiService,
                             NEMApiService nemApiService,
                             XLMApiService xlmApiService,
                             AdaApiService adaApiService,
                             NeoScanApiService neoScanApiService,
                             ZChainApiService zChainApiService,
                             TRXApiService trxApiService,
                             BNBApiService bnbApiService,
                             DotApiService dotApiService) {
        mBTCApiService = btcApiService;
        mETHApiService = ethApiService;
        mEthplorerApiService = ethplorerApiService;
        mChainsoApiService = chainsoApiService;
        mChainzApiService = chainzApiService;
        mXRPApiService = xrpApiService;
        mBCHChainApiService = bchChainApiService;
        mETCApiService = etcApiService;
        mDogeApiService = dogeApiService;
        mNEMApiService = nemApiService;
        mXLMApiService = xlmApiService;
        mAdaApiService = adaApiService;
        mNeoScanApiService = neoScanApiService;
        mZChainApiService = zChainApiService;
        mGasTrackerApiService = gasTrackerApiService;
        mTRXApiService = trxApiService;
        mBNBApiService = bnbApiService;
        mDotApiService = dotApiService;
    }

    public ApiClient provide(String coinTicker) {
        switch (coinTicker) {
            case "BTC":
                return new BTCApiClient(mBTCApiService);
            case "ETH":
                return new ETHApiClient(mEthplorerApiService);
            case "LTC":
                return new LTCApiClient(mChainzApiService);
            case "XRP":
                return new XRPApiClient(mXRPApiService);
            case "DASH":
                return new DashApiClient(mChainzApiService);
            case "BCH":
                return new BCHApiClient(mBCHChainApiService);
            case "ETC":
                return new GasTrackerApiClient(mGasTrackerApiService);
            case "DOGE":
                return new DogeApiClient(mDogeApiService);
            case "XEM":
                return new NEMApiClient(mNEMApiService);
            case "XLM":
                return new XLMApiClient(mXLMApiService);
            case "ADA":
                return new AdaApiClient(mAdaApiService);
            case "NEO":
                return new NeoApiClient(mNeoScanApiService);
            case "ZEC":
                return new ZECApiClient(mZChainApiService);
            case "TRX":
                return new TRXApiClient(mTRXApiService);
            case "BNB":
                return new BNBApiClient(mBNBApiService);
            case "DOT":
                return new DotApiClient(mDotApiService);
        }
        throw new IllegalArgumentException("Coin " + coinTicker + " is unsupported");
    }
}
