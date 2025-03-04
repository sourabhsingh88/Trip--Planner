package com.amstech.tripplanner.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripResponseModal;
import com.amstech.tripplanner.booking.repo.TripRepo;

@Service
public class TripService {

	private final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

	@Autowired
	private TripRepo tripRepo;

	private Integer continueStatusId = 9;

	public TripService() {
		LOGGER.debug("TripService :Object Created");
	}

	public List<TripResponseModal> findAllContinue() throws Exception {

		List<Trip> trips = tripRepo.findAllByContinueStatusId(continueStatusId);
		if (trips.isEmpty()) {
			throw new Exception("No trip Available");
		}
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

	public TripDetailResponseModal findById(Integer id) throws Exception {
		Optional<Trip> tripOptional = tripRepo.findById(id);
		if (!tripOptional.isPresent()) {
			throw new Exception("No trip Available with id : " + id);
		}
		Trip trip = tripOptional.get();
		if (trip.getStatus().getId() != continueStatusId) {
			throw new Exception("That Trip is Not Continue in Current Time where thrip id : " + id);
		}

		TripDetailResponseModal tripDetailResponseModal = new TripDetailResponseModal();
		tripDetailResponseModal.setId(trip.getId());
		tripDetailResponseModal.setName(trip.getName());
		tripDetailResponseModal.setDescription(trip.getDescription());
		tripDetailResponseModal.setFrom(trip.getFromLocation());
		tripDetailResponseModal.setTo(trip.getToLocation());
		tripDetailResponseModal.setPrice(trip.getPrice());
		tripDetailResponseModal.setImgURL(trip.getImgUrl());
		
//		tripDetailResponseModal.setTripPlannerName(trip.getTripPlanner().getUser().g);
		
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
