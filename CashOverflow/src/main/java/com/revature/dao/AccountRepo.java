package com.revature.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.BankAccount;

/**
 * 
 * @author rasco
 *
 */
@Repository
public interface AccountRepo extends JpaRepository<BankAccount, Integer> {
	
	public Optional<BankAccount> findById(Integer id);

}
