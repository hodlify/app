package ru.nikitazhelonkin.coinbalance.data.api.response;

public class DotBalanceResponse {

    public int code;

    public String message;

    public Data data;

    public static class Data {
        public Account account;
    }

    public static class Account {
        public String address;
        public String balance;
    }
}
