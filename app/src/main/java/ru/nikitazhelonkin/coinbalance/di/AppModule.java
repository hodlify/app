package ru.nikitazhelonkin.coinbalance.di;


import androidx.room.Room;
import android.content.Context;
import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.nikitazhelonkin.coinbalance.BuildConfig;
import ru.nikitazhelonkin.coinbalance.data.api.coin.AdaApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.BCHChainApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.BNBApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.BTCApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ChainsoApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ChainzApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.DotApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ETCApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.ETHApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.EthplorerApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.GasTrackerApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.NEMApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.NeoScanApiService;
import ru.nikitazhelonkin.coinbalance.data.api.coin.TRXApiService;
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
import ru.nikitazhelonkin.coinbalance.data.api.interceptor.LoggingInterceptor;
import ru.nikitazhelonkin.coinbalance.data.api.response.DogeApiService;
import ru.nikitazhelonkin.coinbalance.data.api.service.CryptoCompareApiService;
import ru.nikitazhelonkin.coinbalance.data.db.AppDatabase;
import ru.nikitazhelonkin.coinbalance.data.db.migration.Migration1_2;
import ru.nikitazhelonkin.coinbalance.data.db.migration.Migration2_3;
import ru.nikitazhelonkin.coinbalance.data.db.migration.Migration3_4;
import ru.nikitazhelonkin.coinbalance.data.prefs.Prefs;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context.getApplicationContext();
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    @NonNull
    ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Provides
    @Singleton
    @NonNull
    AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(mContext, AppDatabase.class, "app-database")
                .addMigrations(
                        new Migration1_2(),
                        new Migration2_3(),
                        new Migration3_4())
                .build();
    }

    @Provides
    @Singleton
    @NonNull
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LoggingInterceptor());
        }
        return builder.build();
    }

    @Provides
    @Singleton
    @NonNull
    CryptoCompareApiService provideCryptocompareApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://min-api.cryptocompare.com", CryptoCompareApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    BTCApiService provideBTCApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://blockchain.info", BTCApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    ETHApiService provideETHApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.etherscan.io", ETHApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    EthplorerApiService provideEthplorerApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.ethplorer.io", EthplorerApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    ChainzApiService provideChainzApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://chainz.cryptoid.info", ChainzApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    ChainsoApiService provideChainSoApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://chain.so", ChainsoApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    XRPApiService provideXRPApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://data.ripple.com", XRPApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    BCHChainApiService provideBCHChainApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://bch-chain.api.btc.com/", BCHChainApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    ETCApiService provideETCApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://etcchain.com", ETCApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    GasTrackerApiService provideGasTrackerApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.gastracker.io", GasTrackerApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    DogeApiService provideDogeApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://dogechain.info", DogeApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    NEMApiService provideNemApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("http://chain.nem.ninja/", NEMApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    XLMApiService provideXLMApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://horizon.stellar.org", XLMApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    TRXApiService provideTRXApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.trongrid.io", TRXApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    AdaApiService provideAdaApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://explorer.cardano.org", AdaApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    BNBApiService provideBNBApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.bscscan.com", BNBApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    DotApiService provideDotApiService(OkHttpClient httpClient, ObjectMapper objectMapper){
        return provideApiService("https://polkadot.api.subscan.io", DotApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    NeoScanApiService provideNeoScanApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.neoscan.io", NeoScanApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    ZChainApiService provideZChainApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.zcha.in", ZChainApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    BitfinexApiService provideBitfinexApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.bitfinex.com", BitfinexApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    BittrexApiService provideBittrexApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.bittrex.com", BittrexApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    BinanceApiService provideBinanceApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.binance.com", BinanceApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    KrakenApiService provideKrakenApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.kraken.com", KrakenApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    PoloniexApiService providePoloniexApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://poloniex.com", PoloniexApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    HitBTCApiService provideHitBTCApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.hitbtc.com", HitBTCApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    CoinbaseApiService provideCoinbaseApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.coinbase.com", CoinbaseApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    GeminiApiService provideGeminiApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://api.gemini.com", GeminiApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    YoBitApiService provideYobitApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://yobit.net/", YoBitApiService.class, httpClient, objectMapper);
    }

    @Provides
    @Singleton
    @NonNull
    WexApiService provideWexApiService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        return provideApiService("https://wex.nz/", WexApiService.class, httpClient, objectMapper);
    }

    @Provides
    Prefs providePrefs(Context context) {
        return Prefs.get(context);
    }

    private <T> T provideApiService(String url, Class<T> tClass, OkHttpClient httpClient, ObjectMapper objectMapper) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper)).build()
                .create(tClass);
    }

}
