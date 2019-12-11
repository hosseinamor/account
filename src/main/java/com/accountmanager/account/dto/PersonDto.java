package com.accountmanager.account.dto;

public class PersonDto {

    private long id;
    private String name;
    private String lastName;
    private long nationalCode;

    public PersonDto(long id, String name, String lastName, long nationalCode) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public long getNationalCode() {
        return nationalCode;
    }

}
