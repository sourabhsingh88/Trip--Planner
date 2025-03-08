package com.amstech.tripplanner.booking.converter.modal;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Booking;
import com.amstech.tripplanner.booking.entity.TripPlanner;
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;
import com.amstech.tripplanner.booking.modal.response.BookingReaponseModal;

@Component
public class TripPlannerEntityToModalConverter {

	
	public TripPlannerApplyRequestModal findById(TripPlanner tripPlanner) {
		TripPlannerApplyRequestModal tripPlannerApplyRequestModal = new TripPlannerApplyRequestModal();
		tripPlannerApplyRequestModal.setUserId(tripPlanner.getUser().getId());
		tripPlannerApplyRequestModal.setExperience(tripPlanner.getExperience());
		tripPlannerApplyRequestModal.setCompanyName(tripPlanner.getCompanyName());
		tripPlannerApplyRequestModal.setBio(tripPlanner.getBio());
		return tripPlannerApplyRequestModal;
	}
}
