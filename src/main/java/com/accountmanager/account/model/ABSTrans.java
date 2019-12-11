package com.accountmanager.account.model;


public abstract class ABSTrans {

    //check inventory is enough or not
    abstract boolean hasInventory();

    //Deposit or removal inventory
    abstract void changeAccountInventory();
}
