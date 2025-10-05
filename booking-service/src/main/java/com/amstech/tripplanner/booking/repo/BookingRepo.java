package com.amstech.tripplanner.booking.repo;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.Booking;


public interface BookingRepo extends JpaRepository<Booking, Integer> {

	@Query("select b from Booking b where b.user.id =:userId and b.trip.id =:tripId")
	List<Booking> findByUserIdTripId(@Param("userId") int userId, @Param("tripId") int tripId);
	
	@Query("select b from Booking b where b.user.id =:userId")
	List<Booking> findByUserId(@Param("userId") int userId,Pageable pageable);
	
	@Query("select count(b) from Booking b where b.user.id =:userId")
	long countByUserId(@Param("userId") int userId);
	
	@Query("select b from Booking b where b.trip.tripPlanner.id =:tripplannerId")
	List<Booking> findByTripplannerId(@Param("tripplannerId") int tripplannerId,Pageable pageable);
	
	@Query("select count(b) from Booking b where b.trip.tripPlanner.id =:tripplannerId")
	long countByTripplannerId(@Param("tripplannerId") int tripplannerId);
	
	@Query("select b from Booking b")
	List<Booking> findAllBooking(Pageable pageable);
	
	@Query("select count(b) from Booking b")
	long countAllBooking();
}
