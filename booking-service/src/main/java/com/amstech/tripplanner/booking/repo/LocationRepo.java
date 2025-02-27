package com.amstech.tripplanner.booking.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.tripplanner.booking.entity.Location;

public interface LocationRepo extends JpaRepository<Location, Integer> {

}
