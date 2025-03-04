package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.Notification;
import com.amstech.tripplanner.booking.entity.TripPlanner;

public interface TripPlannerRepo extends JpaRepository<TripPlanner, Integer>{


//	@Query("select tp from TripPlanner tp where tp.user.id =:userId")
//	List<Notification> findAllByReceiverId( @Param("receiverId") int receiverId);
}
