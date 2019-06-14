package ru.nikitazhelonkin.coinbalance.data.api.coin;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.api.response.NeoScanBalanceResponse;
import ru.nikitazhelonkin.coinbalance.data.entity.Token;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public class NeoApiClient implements ApiClient {

    private NeoScanApiService mApiService;

    public NeoApiClient(NeoScanApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<WalletBalance> getBalance(String address) {
        return mApiService.balance(address)
                .map(this::mapToWalletBalance);
    }

    private WalletBalance mapToWalletBalance(NeoScanBalanceResponse response){
        String walletBalance = "0";
        List<Token> tokens = new ArrayList<>();
        for (NeoScanBalanceResponse.Balance b : response.getBalances()) {
            if("NEO".equalsIgnoreCase(b.assetSymbol)){
                walletBalance = String.valueOf(b.amount);
            }else {
                tokens.add(new Token(0, response.getAddress(), b.assetSymbol, b.asset, b.amount));
            }
        }
        return new WalletBalance(walletBalance, tokens);
    }

}
