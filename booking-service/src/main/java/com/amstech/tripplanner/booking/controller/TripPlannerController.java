package com.amstech.tripplanner.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.tripplanner.booking.modal.request.BookingCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;

@RestController
@RequestMapping("tripplanner")
public class TripPlannerController {


	private final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	public TripPlannerController() {
		LOGGER.info("TripPlannerController : Object Created");
	}
	
//	@RequestMapping(method = RequestMethod.POST, value ="/apply",consumes = "application/json", produces = "application/json")
//	public ResponseEntity<Object> create(@RequestBody TripPlannerApplyRequestModal tripPlannerApplyRequestModal){
//		
//		LOGGER.info("Appling For trip Plannar with Userid: {} " ,tripPlannerApplyRequestModal.getUserId());
//		try {
//			int id = bookingService.create(bookingCreateRequestModal);
//			return new ResponseEntity<Object>("Successfully Apply For trip Plannar and tripPlannerId : " + id ,HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("Failed to Appling For trip Plannar with Userid due to: {}", e.getMessage(), e);
//			return new ResponseEntity<Object>("Failed to Appling For trip Plannar with Userid due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	

}
