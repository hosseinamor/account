package com.accountmanager.account.service;

import com.accountmanager.account.dao.AccountDao;
import com.accountmanager.account.dto.AccountDto;
import com.accountmanager.account.dto.BaseDto;
import com.accountmanager.account.dto.TransactionDto;
import com.accountmanager.account.dto.TransferDto;
import com.accountmanager.account.entity.Account;
import com.accountmanager.account.entity.Currency;
import com.accountmanager.account.entity.Person;
import com.accountmanager.account.model.Transaction;
import com.accountmanager.account.model.Transfer;
import com.accountmanager.account.utils.Response;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//this class manage bank account
@Service
public class AccountSRV {

    @Autowired
    AccountDao dao;

    @Autowired
    PersonSRV personSRV;

    @Autowired
    CurrencySRV currencySRV;

    public AccountSRV() {
    }


    public String save(AccountDto accountDto) {

        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();

        JSONObject personJson;
        //if user not exist add to database and if was exist just add new account
        if (accountDto.getPersonId() == 0 || !personSRV.hasPerson(accountDto.getPersonId())) {
            personJson = new JSONObject(personSRV.save(accountDto.getPersonName(), accountDto.getPersonLastName()
                    , accountDto.getNationalCode()));
            accountDto.setPersonId(Long.parseLong(personJson.getJSONObject("Response").getString("message")));
        }

        Account account = new Account();
        Currency currency = new Currency();
        Person person = new Person();

        currency.setId(accountDto.getCurrencyId());
        person.setId(accountDto.getPersonId());

        account.setCurrency(currency);
        account.setInventory(accountDto.getInventory());
        account.setPerson(person);

        System.out.println("personId" + accountDto.getPersonId());

        try {
            dao.save(account);
            response.setMessage("Account Saved");
            baseDto.setResponse(response);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            baseDto.setResponseCode(0);
            baseDto.setResponse(response);
        }

        return new Gson().toJson(baseDto);
    }

    public String update(AccountDto accountDto) {
        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();

        try {
            dao.updateInventory(accountDto.getInventory(), accountDto.getAccountId());
            response.setMessage("Inventory Account Updated");
            baseDto.setResponse(response);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            baseDto.setResponseCode(0);
            baseDto.setResponse(response);
        }

        return new Gson().toJson(baseDto);
    }

    public String transaction(TransactionDto transactionDto) {
        BaseDto<Response> baseDto = new BaseDto<>();
        Account account = dao.findById(transactionDto.getAccountId()).get();

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setMoney(transactionDto.getMoney());
        transaction.setAction(transactionDto.getAction());
        transaction.changeAccountInventory();

        AccountDto accountDto = new AccountDto();
        accountDto.setInventory(transaction.getAccount().getInventory());
        accountDto.setAccountId(transaction.getAccount().getId());
        update(accountDto);
        baseDto.setResponse(transaction.getResponse());
        return new Gson().toJson(baseDto);
    }

    public String transfer(TransferDto transferDto) {
        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();

        Account sender;
        Account receiver;

        sender = dao.findById(transferDto.getAccountSenderId()).get();
        receiver = dao.findById(transferDto.getAccountReceiverId()).get();

        Transfer transfer = new Transfer();
        transfer.setSenderMoney(sender);
        transfer.setReceiverMoney(receiver);
        transfer.setMoney(transferDto.getMoney());

        if (sender.getCurrency().equals(receiver.getCurrency())) {
            transfer.changeAccountInventory();

            AccountDto senderDto = new AccountDto();
            AccountDto receiverDto = new AccountDto();

            senderDto.setAccountId(sender.getId());
            senderDto.setInventory(transfer.getSenderMoney().getInventory());

            receiverDto.setAccountId(receiver.getId());
            receiverDto.setInventory(transfer.getReceiverMoney().getInventory());


            update(senderDto);
            update(receiverDto);
        } else {
            response.setMessage("Can not transfer from different currency");
            transfer.setResponse(response);
        }

        baseDto.setResponse(transfer.getResponse());
        return new Gson().toJson(baseDto);
    }

    public String getAllAccount() {
        BaseDto<List<AccountDto>> baseDto = new BaseDto<>();


        List<AccountDto> accountDtoList = new ArrayList<>();
        List<Account> list = new ArrayList<>();

        dao.findAll().forEach(list::add);

        for (Account account : list) {
            AccountDto accountDto = new AccountDto();
            accountDto.setInventory(account.getInventory());
            accountDto.setAccountId(account.getId());
            accountDto.setPersonId(account.getPerson().getId());
            accountDto.setCurrencyId(account.getCurrency().getId());
            accountDto.setNationalCode(account.getPerson().getNationalCode());
            accountDto.setPersonLastName(account.getPerson().getLastName());
            accountDto.setPersonName(account.getPerson().getName());
            accountDtoList.add(accountDto);
        }
        baseDto.setResponse(accountDtoList);
        return new Gson().toJson(baseDto);
    }
}
