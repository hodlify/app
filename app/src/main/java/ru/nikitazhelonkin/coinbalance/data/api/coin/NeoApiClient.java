package ru.nikitazhelonkin.coinbalance.data.api.coin;


import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public class NeoApiClient implements ApiClient {

    private NeoScanApiService mApiService;

    public NeoApiClient(NeoScanApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<WalletBalance> getBalance(String address) {
        return mApiService.balance(address)
                .map(response -> response.getBalance("NEO"))
                .map(WalletBalance::new);
    }
}
