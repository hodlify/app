package ru.nikitazhelonkin.coinbalance.data.api.coin;


import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public class GasTrackerApiClient implements ApiClient {

    private GasTrackerApiService mApiService;

    public GasTrackerApiClient(GasTrackerApiService apiService){
        mApiService = apiService;
    }

    @Override
    public Single<WalletBalance> getBalance(String address) {
        return mApiService.balance(address)
                .map(response->response.balance.ether)
                .map(WalletBalance::new);
    }
}
