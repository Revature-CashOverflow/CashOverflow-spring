package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.UserAccount;

/**
 * Repo for interacting with user accounts in the database.
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

}
