package com.amstech.tripplanner.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.tripplanner.booking.modal.request.BookingCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.BookingUpdateRequestModal;
import com.amstech.tripplanner.booking.modal.response.BookingReaponseModal;
import com.amstech.tripplanner.booking.service.BookingService;

@RestController
@RequestMapping("booking")
public class BookingController {

	private final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	
	public BookingController() {
		LOGGER.info("BookingController : Object Created ");
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/create",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> create(@RequestBody BookingCreateRequestModal bookingCreateRequestModal){
		
		LOGGER.info("Booking Create for trip with id  : {} " ,bookingCreateRequestModal.getTripId());
		try {
			int id = bookingService.create(bookingCreateRequestModal);
			return new ResponseEntity<Object>("Booking Create SuccessFully with id : " + id ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Create Booking due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Create Booking due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value ="/update-status",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateStatus(@RequestBody BookingUpdateRequestModal bookingUpdateRequestModal){
		
		LOGGER.info("Updating Booking Status for id : {} " ,bookingUpdateRequestModal.getId());
		try {
			int statusId = bookingService.updateStatus(bookingUpdateRequestModal);
			return new ResponseEntity<Object>("Booking Status Update SuccessFully and Now Current Status : " + statusId,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Update Booking Status due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Update Booking Status due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/byid",produces = "application/json")
	public ResponseEntity<Object> findById(@RequestParam("id") Integer id){
		
		LOGGER.info("Fetching Details of Booking with id : {} " ,id);
		try {
			BookingReaponseModal bookingReaponseModal = bookingService.findById(id);
			return new ResponseEntity<Object>(bookingReaponseModal,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching Details of Booking with id due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching Details of Booking with id due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(method = RequestMethod.GET, value ="/by-userid",produces = "application/json")
	public ResponseEntity<Object> findByUserId(@RequestParam("userId") Integer userId){
		
		LOGGER.info("Fetching Details of Bookings for UserId : {} " ,userId);
		try {
			List<BookingReaponseModal> bookingReaponseModals = bookingService.findByUserId(userId);
			return new ResponseEntity<Object>(bookingReaponseModals,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching Details of Bookingfor UserId due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching Details of Booking for UserId due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
