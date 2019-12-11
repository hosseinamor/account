/*

run mvn package -D maven.test.skip=true; in terminal project folder and run jar output in target folder

*/
package com.accountmanager.account.controller;

import com.accountmanager.account.dto.*;
import com.accountmanager.account.entity.Currency;
import com.accountmanager.account.service.AccountSRV;
import com.accountmanager.account.service.ConvertorSRV;
import com.accountmanager.account.service.CurrencySRV;
import com.accountmanager.account.service.PersonSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


//All input and output format are Json

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    PersonSRV personSRV;

    @Autowired
    CurrencySRV currencySRV;

    @Autowired
    AccountSRV accountSRV;

    @Autowired
    ConvertorSRV convertorSRV;

    private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());

    public AccountController() {
    }

    //add Currency to database
    /*
        {
	        "name" : "dollar"
        }
    */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/currency")
    public String addCurrency(@RequestBody Currency currency) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return currencySRV.save(currency);
    }


    /* if add personId field to json ,that mean user exist and we want just add new account
    {
            "personId" : 13,
            "currencyId" : 2,
            "personName" : "test",
            "personLastName" : "koraei",
            "nationalCode" : 1234567891,
            "inventory" : 2500000
    }
    */
    //add account with new user
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/account")
    public String addAccount(@RequestBody AccountDto accountDto) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return accountSRV.save(accountDto);
    }

    /*
        {
                "id" : 13,
                "inventory" : 2500000
        }*/
    //just update account inventory
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/account")
    public String updateAccount(@RequestBody AccountDto accountDto) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return accountSRV.update(accountDto);
    }


    /*
    {
            "id" : 13,
            "name" : "hiii",
            "lastName" : "bye",
            "nationalCode" : 147852147
    }
    */
    // Update Person --> input : id, name, lastName, nationalCode by JSON Format
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/person")
    public String updatePerson(@RequestBody PersonDto personDto) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return personSRV.update(personDto);
    }

    /*
    {
            "accountSenderId" : 14,
            "accountReceiverId" : 17,
            "money" : 10000
    }
    */
    //transfer money from one account to another account
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/transfer")
    public String transferMoney(@RequestBody TransferDto transferDto) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return accountSRV.transfer(transferDto);
    }

    /*
    {
            "accountId" : 19,
            "money" : 3000,
            "action" : "برداشت"
    }
    */
    //transaction deposit and removal
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/transaction")
    public String transactionMoney(@RequestBody TransactionDto transactionDto) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return accountSRV.transaction(transactionDto);
    }

    /*
        {
            "dollarCurrency" : 750

        }
        */
    //now convert rial to dollar with base 12000 toman
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/convertor")
    public String convert(@RequestBody ConvertorDto convertorDto) {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return convertorSRV.convertor(convertorDto);
    }

    //todo write get one/all apis (person , account , currency )

    //just call api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/account", produces = {"application/json; charset=UTF-8"})
    public String getAllAccount() {
        String nameOfCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        LOGGER.info("Function Called :" + nameOfCurrMethod);
        return accountSRV.getAllAccount();
    }

}
