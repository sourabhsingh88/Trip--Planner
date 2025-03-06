package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripResponseModal;

@Component
public class TripEntityToModalConverter {

	public List<TripResponseModal> findAll(List<Trip> trips){
		List<TripResponseModal> tripResponseModals = new ArrayList<>();
		for (Trip trip : trips) {
			TripResponseModal tripResponseModal = new TripResponseModal();
			tripResponseModal.setId(trip.getId());
			tripResponseModal.setName(trip.getName());
			tripResponseModal.setDescription(trip.getDescription());
			tripResponseModal.setFrom(trip.getFromLocation());
			tripResponseModal.setTo(trip.getToLocation());
			tripResponseModal.setPrice(trip.getPrice());
			tripResponseModal.setImgURL(trip.getImgUrl());
			tripResponseModals.add(tripResponseModal);
		}
		return tripResponseModals;
	}
	
	public TripDetailResponseModal findById(Trip trip) {

		TripDetailResponseModal tripDetailResponseModal = new TripDetailResponseModal();
		tripDetailResponseModal.setId(trip.getId());
		tripDetailResponseModal.setName(trip.getName());
		tripDetailResponseModal.setDescription(trip.getDescription());
		tripDetailResponseModal.setFrom(trip.getFromLocation());
		tripDetailResponseModal.setTo(trip.getToLocation());
		tripDetailResponseModal.setPrice(trip.getPrice());
		tripDetailResponseModal.setImgURL(trip.getImgUrl());
		tripDetailResponseModal.setTripPlannerName(trip.getTripPlanner().getUser().getName());
		tripDetailResponseModal.setCountryName(trip.getLocation().getCity().getState().getCountry());
		tripDetailResponseModal.setStateName(trip.getLocation().getCity().getState().getName());
		tripDetailResponseModal.setCityName(trip.getLocation().getCity().getName());
		tripDetailResponseModal.setLocationName(trip.getLocation().getName());
		tripDetailResponseModal.setDurationInDays(trip.getDuration());
		tripDetailResponseModal.setStartDate(trip.getStartDate());
		tripDetailResponseModal.setEndDate(trip.getEndDate());
		tripDetailResponseModal.setStatusName(trip.getStatus().getName());
		tripDetailResponseModal.setAccommodations(trip.getAccommodations());
		tripDetailResponseModal.setActivities(trip.getActivities());
		tripDetailResponseModal.setMeals(trip.getMeals());
		tripDetailResponseModal.setTransports(trip.getTransports());
		tripDetailResponseModal.setTripBanners(trip.getTripBanners());
		
		return tripDetailResponseModal;
	}
}
