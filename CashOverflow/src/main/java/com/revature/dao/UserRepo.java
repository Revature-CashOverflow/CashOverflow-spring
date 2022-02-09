package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

/**
 * 
 * @author rasco
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
