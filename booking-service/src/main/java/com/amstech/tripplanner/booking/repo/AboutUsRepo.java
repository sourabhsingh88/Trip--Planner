package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.AboutUs;

public interface AboutUsRepo extends JpaRepository<AboutUs, Integer> {
	@Query("select u from AboutUs u where u.email= :email")
	AboutUs findByemail(@Param("email") String email);  
	
	@Query("select u from AboutUs u where u.phoneNumber= :phoneNumber")
	AboutUs findByPhonenumber(@Param("phoneNumber") String phoneNumber);
}
