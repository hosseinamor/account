package com.accountmanager.account.service;

import com.accountmanager.account.dao.CurrencyDao;
import com.accountmanager.account.dto.BaseDto;
import com.accountmanager.account.entity.Currency;
import com.accountmanager.account.utils.Response;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencySRV {

    @Autowired
    CurrencyDao dao;

    public CurrencySRV() {
    }

    public String save(Currency currency) {


        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();

        currency.setName(currency.getName());
        try {
            dao.save(currency);
            response.setMessage("Currency Saved");
            baseDto.setResponse(response);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            baseDto.setResponseCode(0);
            baseDto.setResponse(response);
        }

        return new Gson().toJson(baseDto);
    }


}
