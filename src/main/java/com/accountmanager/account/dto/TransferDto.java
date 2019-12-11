package com.accountmanager.account.dto;

public class TransferDto {

    private long accountSenderId;
    private long accountReceiverId;
    private long money;

    public TransferDto(long accountSenderId, long accountReceiverId, long money) {
        this.accountSenderId = accountSenderId;
        this.accountReceiverId = accountReceiverId;
        this.money = money;
    }

    public long getAccountSenderId() {
        return accountSenderId;
    }

    public long getAccountReceiverId() {
        return accountReceiverId;
    }

    public long getMoney() {
        return money;
    }

}
