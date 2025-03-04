package com.amstech.tripplanner.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.model.response.TripResponseModel;

import com.amstech.tripplanner.booking.service.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private TripService tripService;

	public TripController() {
		System.out.println("Trip Controller : object Created ");
		// TODO Auto-generated constructor stub
	}

//		1.			***************************   API FIND ALL TRIPS    ***************************

	@RequestMapping(method = RequestMethod.GET, value = "/findAllTrip", produces = "application/json")
	public ResponseEntity<Object> findAllTrip() {
		System.out.println("fetching all trips");

		try {
			List<TripResponseModel> tripResponseModels = tripService.findAllTrip();
			return new ResponseEntity<Object>(tripResponseModels, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<Object>("Failed to fetch trip due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	 		2.			***************************   API FIND  TRIPS BY NAME  ((SEARCH BY NAME ))   ***************************

	@RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
	public ResponseEntity<Object> SearchByName(@RequestParam("name") String name) {
		System.out.println("fetching trip data by name :" + name);
		try {
			TripResponseModel tripResponseModel = tripService.findByName(name);
			return new ResponseEntity<Object>(tripResponseModel, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<Object>("Failed to fetch trip due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//		3.			***************************   API CONTINUE AND DISCONTINUE  ***************************

//@RequestMapping(method = RequestMethod.GET, value = "/continueANDdiscontinue", consumes = "application/json", produces = "application/json")

	@PutMapping("/{tripid}/toggle-status")
	public ResponseEntity<String> toggleTripStatus(@PathVariable Integer tripid) {
		String response = tripService.toggleTripStatus(tripid);
		return ResponseEntity.ok(response);
	}

//	}

//		4.			***************************   TRIP DETAIL of all trip      ***************************

	@RequestMapping(method = RequestMethod.GET, value = "/findAllDetail", produces = "application/json")
	public ResponseEntity<Object> findAllDetail() {
		System.out.println("fetching all trip details");

		try {
			List<TripResponseModel> tripDetailResponseModels = tripService.getAllTripDetail();
			return new ResponseEntity<Object>(tripDetailResponseModels, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<Object>("Failed to fetch tripDetail due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//		5.			***************************   API FIND  TRIPS detail  BY ID  ((SEARCH BY ID ))   ***************************

	@RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
	public ResponseEntity<Object> findById(@RequestParam("id") Integer id) {
		System.out.println("fetching trip data by name :" + id);
		try {
			TripResponseModel tripResponseModel = tripService.findById(id);
			return new ResponseEntity<Object>(tripResponseModel, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<Object>("Failed to fetch trip due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
