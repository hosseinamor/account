package com.accountmanager.account.dao;

import com.accountmanager.account.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDao extends CrudRepository<Currency , Long> {
}
