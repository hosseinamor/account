package com.accountmanager.account.dao;

import com.accountmanager.account.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountDao extends CrudRepository<Account , Long> {

    //update account inventory by id
    @Modifying
    @Transactional
    @Query(value = "Update account SET inventory =:inventory WHERE id = :id" , nativeQuery = true)
    void updateInventory(@Param("inventory") long inventory , @Param("id") long id);
}
