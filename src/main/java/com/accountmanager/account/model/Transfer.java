package com.accountmanager.account.model;

import com.accountmanager.account.entity.Account;
import com.accountmanager.account.utils.Response;

public class Transfer extends ABSTrans {

    private Account senderMoney;
    private Account receiverMoney;
    private Response response = new Response();
    private long money;

    @Override
    public boolean hasInventory() {
        return senderMoney.getInventory() > money;
    }

    @Override
    public void changeAccountInventory() {
        if (hasInventory()) {
            senderMoney.setInventory(senderMoney.getInventory() - money);
            receiverMoney.setInventory(receiverMoney.getInventory() + money);
            response.setMessage("Transfer Done");
        } else response.setMessage("not enough inventory");
    }

    public Transfer() {
    }

    public Account getSenderMoney() {
        return senderMoney;
    }

    public void setSenderMoney(Account senderMoney) {
        this.senderMoney = senderMoney;
    }

    public Account getReceiverMoney() {
        return receiverMoney;
    }

    public void setReceiverMoney(Account receiverMoney) {
        this.receiverMoney = receiverMoney;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
