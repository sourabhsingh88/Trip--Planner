package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.tripplanner.booking.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {

	
}
