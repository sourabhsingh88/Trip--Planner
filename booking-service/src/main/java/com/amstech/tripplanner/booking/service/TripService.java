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

import java.util.jar.Attributes.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;


import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Trip;

import com.amstech.tripplanner.booking.repo.StatusRepo;

import com.amstech.tripplanner.booking.repo.TripRepo;

@Service
public class TripService {

	private final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

	@Autowired
	private TripRepo tripRepo;
	@Autowired
	private StatusRepo statusRepo;

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

//	@Autowired
//	public TripRepo tripRepo;
//
	
//
//	public void tripService() {
//		System.out.println("trip service object created");
//	}
//	
////	 1.  ***************************   API FIND All 	***************************
//
//	public List<TripResponseModel> findAllTrip() throws Exception {
//		List<Trip> tripList = tripRepo.findAll();
//
//		List<TripResponseModel> tripResponseModels = new ArrayList<TripResponseModel>();
//		for (Trip trip : tripList) {
//			TripResponseModel responseModel = new TripResponseModel();
//			responseModel.setId(trip.getId());
//			responseModel.setName(trip.getName());
//			responseModel.setFromLocation(trip.getFromLocation());
//			responseModel.setToLocation(trip.getToLocation()) ;
//			responseModel.setPrice(trip.getPrice());
//			responseModel.setImgUrl(trip.getImgUrl());
//			tripResponseModels.add(responseModel);
//		}
//		return tripResponseModels;
//
//	}
//
////	  2.   ***************************   API FIND  TRIPS BY NAME  ((SEARCH BY NAME ))   ***************************
//	
//	
//	public  TripResponseModel  findByName(String name) throws Exception {
//		Optional<Trip> tripOptional = Optional.ofNullable(tripRepo.findByName(name));
//
//		if (tripOptional.isEmpty()) {
//			throw new Exception("user does not exist");
//		}
//		Trip trip = tripOptional.get();
//		TripResponseModel responseModel = new TripResponseModel();
//		responseModel.setId(trip.getId());
//		responseModel.setName(trip.getName());
//		responseModel.setFromLocation(trip.getFromLocation());
//		responseModel.setToLocation(trip.getToLocation());
//		responseModel.setPrice(trip.getPrice());
//		((TripResponseModel) responseModel).setImgUrl(trip.getImgUrl());
//		return responseModel;
//
//	}
//	
//	
////	3.    ***************************   API CONTINUE TO DISCONTINE AND VISE VERSA    ***************************
//	
//	
//	public String toggleTripStatus(Integer tripId) {
//		Optional<Trip> tripOptional = tripRepo.findById(tripId);
//		if (tripOptional.isPresent()) {
//			Trip trip = tripOptional.get();
//			Integer currentStatusId = trip.getStatus().getId();
//
//			// Toggle status: If 1 (continue), change to 0 (discontinue), else change to 1
//			// (continue)
//			Integer newStatusId = (int) ((currentStatusId == 1) ? 0L : 1L);
//
//			// Fetch the new status from the Status table
//			Optional<Status> statusOptional = statusRepo.findById(newStatusId);
//			if (statusOptional.isPresent()) {
//				trip.setStatus(statusOptional.get());
//				tripRepo.save(trip);
//				return "Trip status updated to: " + statusOptional.get().getName();
//			} else {
//				return "New status not found!";
//			}
//		} else {
//			return "Trip not found!";
//		}
//	}
//	
////  	4.	   	***************************   API FIND  FULL TRIPS DETAIL Of all trips  ***************************
//
//	
//	public List<TripResponseModel> getAllTripDetail() throws Exception {
//		List<Trip> tripList = tripRepo.findAll();
//
//		List<TripResponseModel> tripResponseModels = new ArrayList<TripResponseModel>();
//		
//		for (Trip trip : tripList) {
//			TripResponseModel responseModel = new TripResponseModel();
//			System.out.println("object created");
//			responseModel.setFromLocation(trip.getFromLocation());
//			responseModel.setToLocation(trip.getToLocation());
//			tripResponseModels.add(responseModel);
//		}
//		return tripResponseModels;
//
//	}
//	
////		5.		***************************   API FIND  FULL TRIPS DETAIL by id***************************
////	
////	
////	public List<TripResponseModel> getDetailById(int trip_id) {
//////		List<Trip> trips = tripRepo.findAllById(trip_id);
////		List<TripResponseModel> tripResponseModels = new ArrayList<TripResponseModel>();
//////		for (Trip trip : trips) {
////		TripResponseModel response = new TripResponseModel() ;
////		
////		response.setName(trip.getName()); 
////		}
////        
////    }
//	
//	
//	public TripResponseModel findById(Integer id) throws Exception {
//	Optional<Trip> tripOptional = tripRepo.findById(id);
//
//	if (!tripOptional.isPresent()) {
//		throw new Exception("User does not exist.");
//	}
//
//	Trip trip = tripOptional.get();
//
////	if (trip.getIsDeleted() == 1) {
////		throw new Exception("trip is desctivate.");
////	}
//
//	TripResponseModel responseModel = new TripResponseModel();
//	responseModel.setId(trip.getId());
//	responseModel.setFromLocation(trip.getFromLocation());
//	responseModel.setToLocation(trip.getToLocation());
//	
//	return responseModel;
//}
//	
//}
//>>>>>>> a92680f537a5da9dabab68fe81545304f893225b
