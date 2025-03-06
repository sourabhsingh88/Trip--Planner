package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.*;
public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.email= :email")
	User findByemail(@Param("email") String email);
	
	@Query("select u from User u where u.phoneNumber= :phoneNumber")
	User findByPhonenumber(@Param("phoneNumber") String phoneNumber);
	
	@Query("SELECT u from User u where u.email= :userName OR u.phoneNumber= :userName")
	User findByUserName(@Param("userName") String userName);
	
	@Query("SELECT u FROM User u WHERE (u.email = :userName OR u.phoneNumber = :userName) AND u.password = :password")
	User login(@Param("userName") String userName, @Param("password") String password);
 
}
