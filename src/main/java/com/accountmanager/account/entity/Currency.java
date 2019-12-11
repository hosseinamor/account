package com.accountmanager.account.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Currency entity to save in database
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency"  , cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<Account>();

    public Currency() {
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
}
