package com.amstech.tripplanner.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.tripplanner.booking.modal.request.BookingCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.BookingUpdateRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripPlannerUpdateRequestModel;
import com.amstech.tripplanner.booking.service.BookingService;
import com.amstech.tripplanner.booking.service.TripPlannerService;

@RestController
@RequestMapping("tripplanner")
public class TripPlannerController {

	
	private final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired 
	private  TripPlannerService  tripPlannerService;
	
	
	
	public TripPlannerController() {
		LOGGER.info("TripPlannerController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/apply",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> create(@RequestBody TripPlannerApplyRequestModal tripPlannerApplyRequestModal){
		
		LOGGER.info("Appling For trip Plannar with Userid: {} " ,tripPlannerApplyRequestModal.getUserId());
		try {
			int id = tripPlannerService.apply(tripPlannerApplyRequestModal);
			return new ResponseEntity<Object>("Successfully Apply For trip Plannar and tripPlannerId : " + id ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Appling For trip Plannar with Userid due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Appling For trip Plannar with Userid due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(method = RequestMethod.PUT, value ="/update-status",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateTripPlannerStatus(@RequestBody TripPlannerUpdateRequestModel tripPlannerUpdateRequestModel){
		
		LOGGER.info("Updating TripPlanner Status for id : {} " ,tripPlannerUpdateRequestModel.getId());
		try {
			int statusId = tripPlannerService.updateStatus(tripPlannerUpdateRequestModel);
			return new ResponseEntity<Object>("TripPlanner Status Update SuccessFully and Now Current Status : " + statusId,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Update TripPlanner Status due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Update TripPlanner Status due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
