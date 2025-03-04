package com.amstech.tripplanner.booking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripPlannerRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Service
public class TripPlannerService {

	private final Logger LOGGER = LoggerFactory.getLogger(TripPlannerService.class);

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private TripPlannerRepo tripPlannerRepo;
	
	private int pendingId = 6;

	public TripPlannerService() {
		LOGGER.debug("TripPlannerService : Object Created");
	}

//	public int apply(TripPlannerApplyRequestModal tripPlannerApplyRequestModal) throws Exception {
//		Optional<User> userOptional = userRepo.findById(tripPlannerApplyRequestModal.getUserId());
//		if (!userOptional.isPresent()) {
//			throw new Exception("Seneder Is no Available with id  : " + tripPlannerApplyRequestModal.getUserId());
//		}
//		Optional<Status> statusOptional = statusRepo.findById(pendingId);
//		if (!statusOptional.isPresent()) {
//			throw new Exception("Status Is no Available with id  : " + pendingId);
//		}
//		
//		
//		
//		
//		
//		
//	}

}
