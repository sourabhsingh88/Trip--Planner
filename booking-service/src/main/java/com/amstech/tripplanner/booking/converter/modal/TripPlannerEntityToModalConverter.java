package com.amstech.tripplanner.booking.converter.modal;

import org.springframework.stereotype.Component;
import com.amstech.tripplanner.booking.entity.TripPlanner;
import com.amstech.tripplanner.booking.modal.response.TripPlannerResponseModal;

@Component
public class TripPlannerEntityToModalConverter {

	
	public TripPlannerResponseModal findById(TripPlanner tripPlanner) {
		TripPlannerResponseModal tripPlannerResponseModal = new TripPlannerResponseModal();
		tripPlannerResponseModal.setId(tripPlanner.getId());
		tripPlannerResponseModal.setName(tripPlanner.getUser().getName());
		tripPlannerResponseModal.setEmail(tripPlanner.getUser().getEmail());
		tripPlannerResponseModal.setPhoneNumber(tripPlanner.getUser().getPhoneNumber());
		tripPlannerResponseModal.setBio(tripPlanner.getBio());
		tripPlannerResponseModal.setCompanyName(tripPlanner.getCompanyName());
		tripPlannerResponseModal.setExperience(tripPlanner.getExperience());
		tripPlannerResponseModal.setStatusName(tripPlanner.getStatus().getName());
		return tripPlannerResponseModal;
	}
}
