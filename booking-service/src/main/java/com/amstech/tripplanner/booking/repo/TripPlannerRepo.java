package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.amstech.tripplanner.booking.entity.TripPlanner;

public interface TripPlannerRepo extends JpaRepository<TripPlanner, Integer>{

	@Query("select tp from TripPlanner tp")
	List<TripPlanner> findAllTripPlanner(Pageable pageable);
	
	@Query("select count(tp) from TripPlanner tp")
	long countAllTripPlanner();
	
	@Query("select tp from TripPlanner tp where tp.user.id=:userId")
	List<TripPlanner> findAllByUserId(@RequestParam("userId") Integer userId,Pageable pageable);
	
	@Query("select count(tp) from TripPlanner tp where tp.user.id=:userId")
	long countAllByUserId(@RequestParam("userId") Integer userId);
	
	
	@Query("select tp from TripPlanner tp where tp.user.id=:userId and tp.status.id = 7")
	TripPlanner findApprovedByUserId(@RequestParam("userId") Integer userId);
}
