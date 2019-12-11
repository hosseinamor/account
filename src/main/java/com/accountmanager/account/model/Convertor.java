package com.accountmanager.account.model;

import com.accountmanager.account.utils.Constant;

// this class convert rial to dollar and dollar to rial
public class Convertor {

    public static double currencyConvert(long fCurrency, long sCurrency) {
        if (fCurrency != 0) {
            return (double) fCurrency / Constant.div;
        } else return (double) sCurrency * Constant.div;
    }

    public Convertor() {
    }
}
