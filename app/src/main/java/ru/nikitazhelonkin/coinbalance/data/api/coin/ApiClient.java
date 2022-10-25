package ru.nikitazhelonkin.coinbalance.data.api.coin;


import androidx.annotation.NonNull;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.WalletBalance;

public interface ApiClient {

    Single<WalletBalance> getBalance(@NonNull String address);
}
