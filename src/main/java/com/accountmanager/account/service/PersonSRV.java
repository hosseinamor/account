package com.accountmanager.account.service;

import com.accountmanager.account.dao.PersonDao;
import com.accountmanager.account.dto.BaseDto;
import com.accountmanager.account.dto.PersonDto;
import com.accountmanager.account.entity.Person;
import com.accountmanager.account.utils.Response;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonSRV {

    @Autowired
    private PersonDao dao;

    public PersonSRV() {
    }


    public String save(String name, String lastName, long nationalCode) {
        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();
        Person person = new Person();
        person.setName(name);
        person.setLastName(lastName);
        person.setNationalCode(nationalCode);
        response.setMessage(String.valueOf(dao.save(person).getId()));
        baseDto.setResponse(response);
        return new Gson().toJson(baseDto);
    }

    public boolean hasPerson(long id) {
        try {
            dao.findById(id);
            return true;
        } catch (Throwable throwable) {
            return false;
        }
    }

    public String update(PersonDto personDto) {
        BaseDto<Response> baseDto = new BaseDto<>();
        Response response = new Response();

        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        person.setNationalCode(personDto.getNationalCode());

        try {
            dao.save(person);
            response.setMessage("Person Updated");
            baseDto.setResponse(response);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            baseDto.setResponseCode(0);
            baseDto.setResponse(response);
        }

        return new Gson().toJson(baseDto);
    }
}
