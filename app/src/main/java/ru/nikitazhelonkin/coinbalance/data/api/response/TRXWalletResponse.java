package ru.nikitazhelonkin.coinbalance.data.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TRXWalletResponse {

    private List<Wallet> data;

    @JsonCreator
    public TRXWalletResponse(@JsonProperty("data") List<Wallet> data) {
        this.data = data;
    }

    public String getBalance(){
        return String.valueOf(data.get(0).balance/Math.pow(10, 6));
    }

    public List<Asset> getAssets(){
        return data.get(0).assets;
    }

    public static class Wallet {

        private long balance;

        private List<Asset> assets;


        @JsonCreator
        public Wallet(@JsonProperty("balance") long balance, @JsonProperty("assetV2") List<Asset> assets) {
            this.balance = balance;
            this.assets = assets;
        }

    }

    public static class Asset{
        public String key;
        public String value;
    }


}
