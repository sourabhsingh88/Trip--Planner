package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.tripplanner.booking.entity.Trip;

public interface TripRepo extends JpaRepository<Trip, Integer>{

}
