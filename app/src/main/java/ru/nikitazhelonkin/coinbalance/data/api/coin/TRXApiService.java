package ru.nikitazhelonkin.coinbalance.data.api.coin;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXAssetResponse;
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXWalletResponse;

public interface TRXApiService {

    @GET("v1/accounts/{address}")
    Single<TRXWalletResponse> balance(@Path("address") String address);

    @GET("v1/assets/{id}")
    Single<TRXAssetResponse> getAsset(@Path("id") String id);
}
