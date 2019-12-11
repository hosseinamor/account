package com.accountmanager.account.service;

import com.accountmanager.account.dto.BaseDto;
import com.accountmanager.account.dto.ConvertorDto;
import com.accountmanager.account.model.Convertor;
import com.accountmanager.account.utils.Response;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ConvertorSRV {

    public String convertor(ConvertorDto convertorDto) {
        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();

        response.setMessage(String.valueOf(Convertor.currencyConvert(convertorDto.getRialCurrency(), convertorDto.getDollarCurrency())));
        baseDto.setResponse(response);
        return new Gson().toJson(baseDto);

    }

}
