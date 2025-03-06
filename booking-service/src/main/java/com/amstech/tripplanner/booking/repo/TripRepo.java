package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.Trip;

public interface TripRepo extends JpaRepository<Trip, Integer> {

	@Query("select t from Trip t where t.status.id =:continueStatusId")
	List<Trip> findAllByContinueStatusId(@Param("continueStatusId") Integer continueStatusId);
	
	@Query("SELECT t FROM Trip t WHERE t.name = :name and t.status.id =:continueStatusId")
	Trip findByName(@Param("name") String name,@Param("continueStatusId") Integer continueStatusId);
}

