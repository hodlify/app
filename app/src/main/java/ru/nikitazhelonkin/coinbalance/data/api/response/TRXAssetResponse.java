package ru.nikitazhelonkin.coinbalance.data.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TRXAssetResponse {

    private List<Asset> data;

    public TRXAssetResponse(@JsonProperty("data") List<Asset> data) {
        this.data = data;
    }

    public List<Asset> getData() {
        return data;
    }

    public static class Asset{

        public String abbr;
        public String name;
        public int precision;

    }
}
