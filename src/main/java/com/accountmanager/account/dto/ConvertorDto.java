package com.accountmanager.account.dto;

public class ConvertorDto {

    private long rialCurrency;
    private long dollarCurrency;
    private double div;

    public ConvertorDto(long rialCurrency, long dollarCurrency, double div) {
        this.rialCurrency = rialCurrency;
        this.dollarCurrency = dollarCurrency;
        this.div = div;
    }

    public long getRialCurrency() {
        return rialCurrency;
    }

    public long getDollarCurrency() {
        return dollarCurrency;
    }
}
