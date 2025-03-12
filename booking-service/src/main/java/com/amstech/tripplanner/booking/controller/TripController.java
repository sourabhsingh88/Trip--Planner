package com.amstech.tripplanner.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.tripplanner.booking.modal.request.TripCreateRequestModal;
import com.amstech.tripplanner.booking.modal.response.LocationWithTripResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.TripService;


@RestController
@RequestMapping("trip")
public class TripController {

	private final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

	@Autowired
	private TripService tripService;

	public TripController() {

		LOGGER.info("TripController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json",produces = "application/json")
	public RestResponse create(@RequestBody TripCreateRequestModal tripCreateRequestModal) {

		LOGGER.info("Creating Trip with name : " + tripCreateRequestModal.getName());
		try {
			LocationWithTripResponseModal locationWithTripResponseModal = tripService.create(tripCreateRequestModal);
			return RestResponse.build().withSuccess("SuccessFully Create trip",locationWithTripResponseModal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching All Trips Availables due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Create Trip due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public ResponseEntity<Object> findAll() {

		LOGGER.info("Fetching All Trips Available");
		try {
			List<TripResponseModal> tripResponseModals = tripService.findAllContinue();
			return new ResponseEntity<Object>(tripResponseModals, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching All Trips Availables due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching All Trips Availables due to : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byid", produces = "application/json")
	public ResponseEntity<Object> findById(@RequestParam("id") Integer id) {

		LOGGER.info("Fetching Details of Trip with id : {}", id);
		try {
			TripDetailResponseModal tripResponseModal = tripService.findById(id);
			return new ResponseEntity<Object>(tripResponseModal, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching Details of Trip with id due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching Details of Trip with id due to : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
	public ResponseEntity<Object> SearchByName(@RequestParam("name") String name) {
		LOGGER.info("fetching trip data by name : {}" , name);
		try {
			List<TripResponseModal> tripResponseModal = tripService.findByName(name);
			return new ResponseEntity<Object>(tripResponseModal, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Failed to fetch trip due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateStatus", produces = "application/json")
	public ResponseEntity<Object> toggleTripStatus(@RequestParam("id") Integer id) {
		LOGGER.info("Updateing trip Status by statusId : {}" , id);
		try {
			String tripStatus = tripService.toggleTripStatus(id);
			return new ResponseEntity<Object>("SuccessFully Update the the status of tripid"+ id +" is " + tripStatus,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to update status due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to update status due to "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
