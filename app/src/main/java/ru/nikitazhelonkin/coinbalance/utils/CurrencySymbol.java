package ru.nikitazhelonkin.coinbalance.utils;

import java.util.Currency;

public class CurrencySymbol {

    public static String forCurrency(String currencyStr) {
        Currency currency = Currency.getInstance(currencyStr);
        return "BTC".equals(currencyStr) ? "Ƀ" : currency.getSymbol();
    }
}
