package ru.nikitazhelonkin.coinbalance.data.api.coin;


import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public interface ApiClient {

    Single<WalletBalance> getBalance(String address);
}
