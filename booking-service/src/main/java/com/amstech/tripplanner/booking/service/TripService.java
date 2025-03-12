package com.amstech.tripplanner.booking.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.modal.request.TripCreateRequestModal;
import com.amstech.tripplanner.booking.modal.response.LocationWithTripResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripResponseModal;
import com.amstech.tripplanner.booking.converter.entity.TripModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.LocationEntityToModalConverter;
import com.amstech.tripplanner.booking.converter.modal.TripEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.repo.LocationRepo;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripRepo;

@Service
public class TripService {  

	private final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

	@Autowired
	private TripRepo tripRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Autowired
	private TripEntityToModalConverter tripEntityToModalConverter;
	@Autowired
	private TripModalToEntityConverter tripModalToEntityConverter;
	@Autowired 
	private LocationEntityToModalConverter locationEntityToModalConverter;

	private Integer continueStatusId = 9;
	private Integer disContinueStatusId = 10;

	public TripService() {
		LOGGER.debug("TripService :Object Created");
	}

	public LocationWithTripResponseModal create(TripCreateRequestModal tripCreateRequestModal) throws Exception {
		Location tripCreatewithLocation = tripModalToEntityConverter.tripCreate(tripCreateRequestModal);
		Location saveLocationWithTrip = locationRepo.save(tripCreatewithLocation);
		return  locationEntityToModalConverter.findBy(saveLocationWithTrip);
	}
	
	public List<TripResponseModal> findAllContinue() throws Exception {

		List<Trip> trips = tripRepo.findAllByContinueStatusId(continueStatusId);
		if (trips.isEmpty()) {
			throw new Exception("No trip Available");
		}
		List<TripResponseModal> tripResponseModals = tripEntityToModalConverter.findAll(trips);
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

		TripDetailResponseModal tripDetailResponseModal = tripEntityToModalConverter.findById(trip);
		return tripDetailResponseModal;

	}

	public List<TripResponseModal> findByName(String name) throws Exception {
		List<Trip> trips = tripRepo.searchBy(name, continueStatusId);

		if (trips.isEmpty()) {
			throw new Exception("user does not exist");
		}
		List<TripResponseModal> tripResponseModals = tripEntityToModalConverter.findAll(trips);
		return tripResponseModals;

	}
	public String toggleTripStatus(Integer tripId) throws Exception {
		Optional<Trip> tripOptional = tripRepo.findById(tripId);
		
		if(!tripOptional.isPresent()) {
			throw new Exception("That trip is Not Available with id " + tripId);
		}
			Trip trip = tripOptional.get();
			Integer currentStatusId = trip.getStatus().getId();

			// Toggle status: If 1 (continue), change to 0 (discontinue), else change to 1
			// (continue)
			Integer newStatusId = (currentStatusId == 9) ? 10 : 9;

			// Fetch the new status from the Status table
			Optional<Status> statusOptional = statusRepo.findById(newStatusId);
			if (!statusOptional.isPresent()) {
				throw new Exception("That tripStaus is Not Available in Your Database with id " + newStatusId);
			} 
			trip.setStatus(statusOptional.get());
			
			Trip updateTrip = tripRepo.save(trip);
			return updateTrip.getStatus().getName();
		} 
	}

