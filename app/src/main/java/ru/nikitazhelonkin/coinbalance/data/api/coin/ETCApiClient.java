package ru.nikitazhelonkin.coinbalance.data.api.coin;


import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public class ETCApiClient implements ApiClient {


    private ETCApiService mApiService;

    public ETCApiClient(ETCApiService apiService){
        mApiService = apiService;
    }

    @Override
    public Single<WalletBalance> getBalance(String address) {
        return mApiService.balance(address)
                .map(etcResponse -> etcResponse.balance)
                .map(WalletBalance::new);
    }
}
