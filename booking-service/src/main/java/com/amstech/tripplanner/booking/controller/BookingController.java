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
import com.amstech.tripplanner.booking.response.RestResponse;
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
	public RestResponse create(@RequestBody BookingCreateRequestModal bookingCreateRequestModal){
		
		LOGGER.info("Booking Create for trip with id  : {} " ,bookingCreateRequestModal.getTripId());
		try {
			BookingReaponseModal bookingReaponseModal = bookingService.create(bookingCreateRequestModal);
			return RestResponse.build().withSuccess("Booking Create SuccessFully",bookingReaponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Create Booking due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Create Booking due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value ="/updateStatus",consumes = "application/json", produces = "application/json")
	public RestResponse updateStatus(@RequestBody BookingUpdateRequestModal bookingUpdateRequestModal){
		
		LOGGER.info("Updating Booking Status for id : {} " ,bookingUpdateRequestModal.getId());
		try {
			BookingReaponseModal bookingReaponseModal = bookingService.updateStatus(bookingUpdateRequestModal);
			return RestResponse.build().withSuccess("Booking Status Update SuccessFully",bookingReaponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Update Booking Status due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Update Booking Status due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/byId",produces = "application/json")
	public RestResponse findById(@RequestParam("id") Integer id){
		
		LOGGER.info("Fetching Details of Booking with id : {} " ,id);
		try {
			BookingReaponseModal bookingReaponseModal = bookingService.findById(id);
			return RestResponse.build().withSuccess("Booking Found",bookingReaponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Fetching Details of Booking with id due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Booking Found due to : " + e.getMessage());
		}
	}
	

	@RequestMapping(method = RequestMethod.GET, value ="/byUserId",produces = "application/json")
	public RestResponse findByUserId(@RequestParam("userId") Integer userId,@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		
		LOGGER.info("Fetching Details of Bookings for UserId : {} " ,userId);
		try {
			List<BookingReaponseModal> bookingReaponseModals = bookingService.findByUserId(userId, page, size);
			long totalRecords = bookingService.countByUserId(userId);
			return RestResponse.build().withSuccess("Booking Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(bookingReaponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed to Fetching Details of Bookingfor UserId due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Booking Found due to : " + e.getMessage());
		}
	}
	@RequestMapping(method = RequestMethod.GET, value ="/byTripPlannerId",produces = "application/json")
	public RestResponse findByTripPlannerId(@RequestParam("tripplannerId") Integer tripplannerId,@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		
		LOGGER.info("Fetching Details of Bookings for TripplannerId : {} " ,tripplannerId);
		try {
			List<BookingReaponseModal> bookingReaponseModals = bookingService.findByTripplannerId(tripplannerId, page, size);
			long totalRecords = bookingService.countByTripplanner(tripplannerId);
			return RestResponse.build().withSuccess("Booking Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(bookingReaponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed to Fetching Details of Booking for tripplanner due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Booking Found due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/all",produces = "application/json")
	public RestResponse findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		
		LOGGER.info("Fetching Details of Bookings  ");
		try {
			List<BookingReaponseModal> bookingReaponseModals = bookingService.findAll(page, size);
			long totalRecords = bookingService.countAllBooking();
			return RestResponse.build().withSuccess("Booking Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(bookingReaponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed to Fetching Details of Bookingfor UserId due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Booking Found due to : " + e.getMessage());
		}
	}

}
