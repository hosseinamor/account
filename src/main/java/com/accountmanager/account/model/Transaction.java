package com.accountmanager.account.model;

import com.accountmanager.account.entity.Account;
import com.accountmanager.account.utils.Constant;
import com.accountmanager.account.utils.Response;

public class Transaction extends ABSTrans {

    private Account account = new Account();
    private long money;
    private String action;
    private Response response = new Response();


    @Override
    public boolean hasInventory() {
        return money < account.getInventory();
    }

    @Override
    public void changeAccountInventory() {
        if (action.equals(Constant.REMOVAL) && hasInventory()) {
            account.setInventory(account.getInventory() - money);
            response.setMessage("Removal Done");
        } else if (action.equals(Constant.DEPOSIT)) {
            long temp = account.getInventory() + money;
            account.setInventory(temp);
            response.setMessage("Deposit Done");
        } else response.setMessage("not enough inventory");
    }

    public Transaction() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Response getResponse() {
        return response;
    }

}
