package com.accountmanager.account.dao;

import com.accountmanager.account.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person , Long> {
}
