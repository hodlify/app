package ru.nikitazhelonkin.coinbalance.data.api.exchange;


import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.nikitazhelonkin.coinbalance.data.api.response.BittrexBalance;

public interface BittrexApiService {

    @GET("v3/balances")
    Single<List<BittrexBalance>> balances(@Header("Api-Key") String apiKey,
                                          @Header("Api-Timestamp") String timestamp,
                                          @Header("Api-Content-Hash") String contentHash,
                                          @Header("Api-Signature") String signature);
}
