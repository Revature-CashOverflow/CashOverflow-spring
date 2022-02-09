package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Account;

/**
 * 
 * @author rasco
 *
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

}
