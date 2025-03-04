package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.Trip;

public interface TripRepo extends JpaRepository<Trip, Integer> {

	
	
	@Query("SELECT e FROM Trip e WHERE e.name = :name")
	Trip findByName(@Param("name") String name);


}