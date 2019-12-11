package com.accountmanager.account.dto;

public class TransactionDto {

    private long accountId;
    private long money;
    private String action;

    public TransactionDto(long accountId, long money, String action) {
        this.accountId = accountId;
        this.money = money;
        this.action = action;
    }

    public long getAccountId() {
        return accountId;
    }


    public long getMoney() {
        return money;
    }


    public String getAction() {
        return action;
    }

}
