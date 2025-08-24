package com.amstech.tripplanner.booking.converter.entity;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.TripPlanner;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripPlannerUpdateRequestModel;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripPlannerRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Component
public class TripPlannerModalToEntityConverter {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private TripPlannerRepo tripPlannerRepo;
	
	private int pendingId = 6;
	
	
	public TripPlanner apply(TripPlannerApplyRequestModal tripPlannerApplyRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(tripPlannerApplyRequestModal.getUserId());
		if (!userOptional.isPresent()) {
			throw new Exception("Seneder Is no Available with id  : " + tripPlannerApplyRequestModal.getUserId());
		}
		Optional<Status> statusOptional = statusRepo.findById(pendingId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + pendingId);
		}
		TripPlanner tripPlanner =new TripPlanner();
		tripPlanner.setUser(userOptional.get());
		tripPlanner.setStatus(statusOptional.get());
		tripPlanner.setExperience(tripPlannerApplyRequestModal.getExperience());
		tripPlanner.setCompanyName(tripPlannerApplyRequestModal.getCompanyName());
		tripPlanner.setBio(tripPlannerApplyRequestModal.getBio());
		tripPlanner.setCreatedAt(new Date());
		tripPlanner.setUpdatedAt(new Date());
		return tripPlanner;
	}
	
	public TripPlanner updateStatus(TripPlannerUpdateRequestModel tripPlannerUpdateRequestModel) throws Exception {
		Optional<TripPlanner> tripPlannerOptional = tripPlannerRepo.findById(tripPlannerUpdateRequestModel.getId());
		if (!tripPlannerOptional.isPresent()) {
			throw new Exception("Booking Is no Available with id  : " + tripPlannerUpdateRequestModel.getId());
		}
		Optional<Status> statusOptional = statusRepo.findById(tripPlannerUpdateRequestModel.getStatusId());
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + tripPlannerUpdateRequestModel.getStatusId());
		}

		if (tripPlannerOptional.get().getStatus().getId() == statusOptional.get().getId()) {
			throw new Exception("Booking Is Already in " + statusOptional.get().getName() + " Status.");
		}
		TripPlanner tripPlanner = tripPlannerOptional.get();
		tripPlanner.setId(tripPlannerUpdateRequestModel.getId());
		tripPlanner.setStatus(statusOptional.get());
		return tripPlanner;
	}
}
