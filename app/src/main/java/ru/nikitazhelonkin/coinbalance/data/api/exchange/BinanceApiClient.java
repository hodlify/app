package ru.nikitazhelonkin.coinbalance.data.api.exchange;


import java.util.HashMap;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer;
import ru.nikitazhelonkin.coinbalance.data.api.response.BinanceBalancesResponse;
import ru.nikitazhelonkin.coinbalance.data.api.exchange.BinanceApiService;
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil;

public class BinanceApiClient implements ExchangeApiClient {

    private BinanceApiService mApiService;

    public BinanceApiClient(BinanceApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<HashMap<String, Float>> getBalances(String apiKey, String apiSecret) {
        long time = mApiService.time().blockingGet().getServerTime();
        String timeStamp = String.valueOf(time);
        String queryString = String.format("timestamp=%s",  timeStamp);
        String signature = DigestUtil.hmacString(queryString, apiSecret, "HmacSHA256");
        return mApiService.balances(timeStamp, signature, apiKey).map(BinanceBalancesResponse::getNonZeroBalances)
                .compose(new HttpErrorTransformer<>());
    }
}
