package com.amstech.tripplanner.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amstech.tripplanner.booking.entity.*;
public interface UserRepo extends JpaRepository<User, Integer>{
 
}
