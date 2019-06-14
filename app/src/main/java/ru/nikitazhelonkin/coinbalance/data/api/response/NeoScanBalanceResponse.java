package ru.nikitazhelonkin.coinbalance.data.api.response;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class NeoScanBalanceResponse {

    private List<Balance> balances;

    private String address;

    public NeoScanBalanceResponse(  @JsonProperty("balance")List<Balance> balances,  @JsonProperty("address") String address) {
        this.balances = balances;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public static class Balance {

        @JsonProperty("asset_symbol")
        public String assetSymbol;

        public String asset;

        public float amount;
    }
}
