package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {

	@Query("select c from City c where c.state.id=:stateId")
	List<City> findAllByStateId(@Param("stateId") int stateId);
}
 