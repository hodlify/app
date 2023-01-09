package ru.nikitazhelonkin.coinbalance.data.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BittrexBalance {
    @JsonProperty("currencySymbol")
    public String currency;
    @JsonProperty("total")
    public String balance;
    @JsonProperty("available")
    public String available;
}