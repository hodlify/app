package ru.nikitazhelonkin.coinbalance.data.api.exchange;


import java.util.HashMap;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.api.HttpErrorTransformer;
import ru.nikitazhelonkin.coinbalance.data.api.response.BittrexBalancesResponse;
import ru.nikitazhelonkin.coinbalance.utils.DigestUtil;

public class BittrexApiClient implements ExchangeApiClient {

    private BittrexApiService mApiService;

    public BittrexApiClient(BittrexApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<HashMap<String, Float>> getBalances(String apiKey, String apiSecret) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String contentHash = DigestUtil.sha512String("");
        String preSignString = timestamp + "https://api.bittrex.com/v3/balances" + "GET" + contentHash;
        String signature = DigestUtil.hmacString(preSignString, apiSecret, "HmacSHA512");
        return mApiService.balances(apiKey, timestamp, contentHash, signature)
                .map(BittrexBalancesResponse::getNonZeroBalances)
                .compose(new HttpErrorTransformer<>());
    }
}
