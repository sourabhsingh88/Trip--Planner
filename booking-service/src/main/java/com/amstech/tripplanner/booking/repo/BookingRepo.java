package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.Booking;


public interface BookingRepo extends JpaRepository<Booking, Integer> {

	@Query("select b from Booking b where b.user.id =:userId and b.trip.id =:tripId")
	List<Booking> findByUserIdTripId(@Param("userId") int userId, @Param("tripId") int tripId);
	
	@Query("select b from Booking b where b.user.id =:userId")
	List<Booking> findByUserId(@Param("userId") int userId);
	
}
