package ru.nikitazhelonkin.coinbalance.data.api.response;



import java.util.HashMap;
import java.util.List;

public class BittrexBalancesResponse {
    
    public static HashMap<String, Float> getNonZeroBalances(List<BittrexBalance> balances) {
        HashMap<String, Float> map = new HashMap<>();
        for (BittrexBalance b : balances) {
            Float value = Float.parseFloat(b.balance);
            if (value != 0)
                map.put(coinTicker(b.currency), value);
        }
        return map;
    }

    private static String coinTicker(String currency) {
        if ("BCC".equalsIgnoreCase(currency))
            currency = "BCH";
        return currency.toUpperCase();
    }

}
