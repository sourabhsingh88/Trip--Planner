package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.tripplanner.booking.entity.Status;

public interface StatusRepo extends JpaRepository<Status, Integer>{

}
