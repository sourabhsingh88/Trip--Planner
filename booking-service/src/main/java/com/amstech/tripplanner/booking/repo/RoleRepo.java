package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.tripplanner.booking.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
