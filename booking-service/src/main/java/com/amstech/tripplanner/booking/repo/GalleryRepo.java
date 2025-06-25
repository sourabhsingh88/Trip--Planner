package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amstech.tripplanner.booking.entity.Gallery;
import com.amstech.tripplanner.booking.entity.User;

public interface GalleryRepo extends JpaRepository<Gallery, Integer> {
	@Query("select u from Gallery u ")
	List<Gallery> findAllImage(Pageable pageable);
	@Query("select count(u) from Gallery u ")
	long countAllImage();
}
