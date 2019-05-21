package ru.nikitazhelonkin.coinbalance.data.api.coin;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nikitazhelonkin.coinbalance.data.api.response.TRXResponse;

public interface TRXApiService {

    @GET("v1/accounts/{address}")
    Single<TRXResponse> balance(@Path("address") String address);
}
