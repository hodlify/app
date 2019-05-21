package ru.nikitazhelonkin.coinbalance.data.api.coin;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nikitazhelonkin.coinbalance.data.api.response.ZChainBalanceResponse;

public interface ZChainApiService {

    @GET("v2/mainnet/accounts/{address}")
    Single<ZChainBalanceResponse> balance(@Path("address") String address);

}
