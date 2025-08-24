package com.amstech.tripplanner.booking.repo;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.amstech.tripplanner.booking.entity.Trip;

public interface TripRepo extends JpaRepository<Trip, Integer> {

	@Query("select t from Trip t where t.status.id =:continueStatusId")
	List<Trip> findAllByContinueStatusId(@Param("continueStatusId") Integer continueStatusId,Pageable pageable);
	
	@Query("select count(t) from Trip t where t.status.id =:continueStatusId")
	long countAllByContinueStatusId(@Param("continueStatusId") Integer continueStatusId);
	
	@Query("select t from Trip t where t.status.id =:continueStatusId and t.tripPlanner.id =:tripPlannerId")
	List<Trip> findAllByContinueStatusIdAndTripplannerId(@Param("continueStatusId") Integer continueStatusId,@Param("tripPlannerId") Integer tripPlannerId,Pageable pageable);
	
	@Query("select count(t) from Trip t where t.status.id =:continueStatusId and t.tripPlanner.id =:tripPlannerId")
	long countAllByContinueStatusIdAndTripplannerId(@Param("continueStatusId") Integer continueStatusId,@Param("tripPlannerId") Integer tripPlannerId);
	
	@Query("select t from Trip t where t.status.id =:discontinueStatusId and t.tripPlanner.id =:tripPlannerId")
	List<Trip> findAllByDiscontinueStatusIdAndTripplannerId(@Param("discontinueStatusId") Integer discontinueStatusId,@Param("tripPlannerId") Integer tripPlannerId,Pageable pageable);
	
	@Query("select count(t) from Trip t where t.status.id =:discontinueStatusId and t.tripPlanner.id =:tripPlannerId")
	long countAllByDiscontinueStatusIdAndTripplannerId(@Param("discontinueStatusId") Integer discontinueStatusId,@Param("tripPlannerId") Integer tripPlannerId);
	
	@Query("SELECT t FROM Trip t WHERE t.name = :name and t.status.id =:continueStatusId")
	List<Trip> searchBy(@Param("name") String name,@Param("continueStatusId") Integer continueStatusId,Pageable pageable);
	
	@Query("SELECT count(t) FROM Trip t WHERE t.name = :name and t.status.id =:continueStatusId")
	long countSearchBy(@Param("name") String name,@Param("continueStatusId") Integer continueStatusId);
}

