package ru.nikitazhelonkin.coinbalance.di;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.nikitazhelonkin.coinbalance.data.api.coin.BNBApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.TRXApiService;
import ru.nikitazhelonkin.coinbalance.data.api.response.DogeApiService;
import ru.nikitazhelonkin.coinbalance.data.api.service.CryptoCompareApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.AdaApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.BCHChainApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.BTCApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ChainsoApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ChainzApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ETCApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ETHApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.EthplorerApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.GasTrackerApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.NEMApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.NeoScanApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.XLMApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.XRPApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ZChainApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.BinanceApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.BitfinexApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.BittrexApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.CoinbaseApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.GeminiApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.HitBTCApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.KrakenApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.PoloniexApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.WexApiService;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.YoBitApiService;
import ru.nikitazhelonkin.coinbalance.data.db.AppDatabase;
import ru.nikitazhelonkin.coinbalance.data.prefs.Prefs;
import ru.nikitazhelonkin.coinbalance.data.repository.ExchangeBalancesRepository;
import ru.nikitazhelonkin.coinbalance.data.repository.ExchangeRepository;
import ru.nikitazhelonkin.coinbalance.data.repository.TokenRepository;
import ru.nikitazhelonkin.coinbalance.data.repository.WalletRepository;
import ru.nikitazhelonkin.coinbalance.domain.PriceInteractor;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();

    Prefs prefs();

    AppDatabase appDatabase();

    CryptoCompareApiService cryptoCompareApiService();

    BTCApiService btcApiService();

    ETHApiService ethApiService();

    EthplorerApiService ethplorerApiService();

    ChainzApiService chainzApiService();

    ChainsoApiService chainsoApiService();

    BNBApiService bnbApiService();

    XRPApiService xrpApiService();

    BCHChainApiService bchChainApiService();

    ETCApiService etcApiService();

    GasTrackerApiService gastrackerApiService();

    DogeApiService dogeApiService();

    NEMApiService nemApiService();

    XLMApiService xlmApiService();

    AdaApiService adaApiService();

    TRXApiService trxApiService();

    NeoScanApiService neoScanApiService();

    ZChainApiService zChainApiService();

    BitfinexApiService bitfinexApiService();

    BittrexApiService bittrexApiService();

    BinanceApiService binanceApiService();

    KrakenApiService krakenApiService();

    PoloniexApiService poloniexApiService();

    HitBTCApiService hitBtcApiService();

    CoinbaseApiService coinbaseApiService();

    GeminiApiService geminiApiService();

    YoBitApiService yoBitApiService();

    WexApiService wexApiService();

    WalletRepository walletRepository();

    TokenRepository tokenRepository();

    ExchangeRepository exchangeRepository();

    ExchangeBalancesRepository exchangeBalancesRepository();

    PriceInteractor pricesInteractor();

}
