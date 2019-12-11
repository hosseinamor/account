package com.accountmanager.account.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//User entity to save in database
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_code")
    private long nationalCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person"  , cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<Account>();

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }
}
