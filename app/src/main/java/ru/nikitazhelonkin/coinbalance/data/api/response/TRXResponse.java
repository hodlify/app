package ru.nikitazhelonkin.coinbalance.data.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TRXResponse {

    @JsonCreator
    public TRXResponse(@JsonProperty("data") List<Data> data) {
        this.data = data;
    }

    private List<Data> data;

    public static class Data{

        private long balance;

        @JsonCreator
        public Data(@JsonProperty("balance") long balance) {
            this.balance = balance;
        }
    }

    public String getBalance(){
        return String.valueOf(data.get(0).balance/Math.pow(10, 6));
    }


}
