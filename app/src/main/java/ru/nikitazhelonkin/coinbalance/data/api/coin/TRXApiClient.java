package ru.nikitazhelonkin.coinbalance.data.api.coin;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXAssetResponse;
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXWalletResponse;
import ru.nikitazhelonkin.coinbalance.data.entity.Token;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public class TRXApiClient implements ApiClient {

    private TRXApiService mApiService;

    public TRXApiClient(TRXApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<WalletBalance> getBalance(String address) {
        return mApiService.balance(address)
                .map(trxResponse -> new WalletBalance(trxResponse.getBalance(), getTokens(address, trxResponse)));
    }

    private List<Token> getTokens(String address, TRXWalletResponse trxResponse){
        return Observable.fromIterable(trxResponse.getAssets())
                .map(asset -> {
                    TRXAssetResponse response = mApiService.getAsset(asset.key).blockingGet();
                    TRXAssetResponse.Asset assetInfo = response.getData().get(0);
                    float balance = (float)( Long.parseLong(asset.value)/Math.pow(10, assetInfo.precision));
                    return new Token(0, address, assetInfo.abbr, assetInfo.name, balance );
                })
                .toList()
                .blockingGet();
    }


}
