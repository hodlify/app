package ru.nikitazhelonkin.coinbalance.data.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BinanceTimeResponse {

    private long serverTime;

    public BinanceTimeResponse(@JsonProperty("serverTime") long serverTime){
        this.serverTime = serverTime;
    }

    public long getServerTime() {
        return serverTime;
    }
}
