package ru.nikitazhelonkin.coinbalance.data.api.service.coin;


import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BTCApiService {

    @GET("q/addressbalance/{address}")
    Single<ResponseBody> balance(@Path("address") String address);


}
