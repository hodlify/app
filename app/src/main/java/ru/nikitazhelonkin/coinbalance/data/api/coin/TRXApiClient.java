package ru.nikitazhelonkin.coinbalance.data.api.coin;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXResponse;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public class TRXApiClient implements ApiClient {

    private TRXApiService mApiService;

    public TRXApiClient(TRXApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<WalletBalance> getBalance(String address) {
        return mApiService.balance(address)
                .map(TRXResponse::getBalance)
                .map(WalletBalance::new);
    }
}
