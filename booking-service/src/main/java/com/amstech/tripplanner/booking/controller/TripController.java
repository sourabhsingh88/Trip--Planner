package com.amstech.tripplanner.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripResponseModal;
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
	
	@RequestMapping(method = RequestMethod.GET, value ="/all",produces = "application/json")
	public ResponseEntity<Object> findAll(){
		
		LOGGER.info("Fetching All Trips Available");
		try {
			List<TripResponseModal> tripResponseModals = tripService.findAllContinue();
			return new ResponseEntity<Object>(tripResponseModals,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching All Trips Availables due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching All Trips Availables due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/byid",produces = "application/json")
	public ResponseEntity<Object> findById(@RequestParam("id") Integer id){
		
		LOGGER.info("Fetching Details of Trip with id : {}" ,id);
		try {
			TripDetailResponseModal tripResponseModal = tripService.findById(id);
			return new ResponseEntity<Object>(tripResponseModal,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching Details of Trip with id due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching Details of Trip with id due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	
}
