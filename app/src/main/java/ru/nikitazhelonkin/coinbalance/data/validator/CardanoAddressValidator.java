package ru.nikitazhelonkin.coinbalance.data.validator;


import android.text.TextUtils;

public class CardanoAddressValidator implements AddressValidator {

    @Override
    public boolean isValid(String address) {
        //TODO validation
        return !TextUtils.isEmpty(address);
    }
}
