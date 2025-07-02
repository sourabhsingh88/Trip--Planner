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
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripPlannerUpdateRequestModel;
import com.amstech.tripplanner.booking.modal.response.TripPlannerResponseModal;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
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
	public RestResponse create(@RequestBody TripPlannerApplyRequestModal tripPlannerApplyRequestModal){
		
		LOGGER.info("Appling For trip Plannar with Userid: {} " ,tripPlannerApplyRequestModal.getUserId());
		try {
			TripPlannerResponseModal tripPlannerResponseModal = tripPlannerService.apply(tripPlannerApplyRequestModal);
			return RestResponse.build().withSuccess("Successfully Apply For trip Plannar", tripPlannerResponseModal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Appling For trip Plannar with Userid due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Appling For trip Plannar with Userid due to : " + e.getMessage());
		}
	}
	@RequestMapping(method = RequestMethod.PUT, value ="/updateStatus",consumes = "application/json", produces = "application/json")
	public RestResponse updateTripPlannerStatus(@RequestBody TripPlannerUpdateRequestModel tripPlannerUpdateRequestModel){
		
		LOGGER.info("Updating TripPlanner Status for id : {} " ,tripPlannerUpdateRequestModel.getId());
		try {
			TripPlannerResponseModal tripPlannerResponseModal = tripPlannerService.updateStatus(tripPlannerUpdateRequestModel);
			return RestResponse.build().withSuccess("Successfully Update Status For trip Plannar", tripPlannerResponseModal);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Update TripPlanner Status due to : {}", e.getMessage(), e);

			return RestResponse.build().withError("Failed to Update TripPlanner Status due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/byId", produces = "application/json")
	public RestResponse findById(@RequestParam("id") Integer id) {
		LOGGER.info("start Finding Trip Planner details with id : {}", id);
		try {
			TripPlannerResponseModal triplPlannerResponseModal = tripPlannerService.findById(id);
			return RestResponse.build().withSuccess("SuccessFully TripPlanner Found", triplPlannerResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Find User due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withSuccess("Failed To Find TripPlanner due to : " + e.getMessage());
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/allByUserId", produces = "application/json")
	public RestResponse findAllByUserId(@RequestParam("userId") Integer userId,@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		LOGGER.info("Fetching All Active User");
		try {
			List<TripPlannerResponseModal> tripPlannerResponseModals = tripPlannerService.findAllByUserId(userId, page, size);
			long totalRecords = tripPlannerService.countAllByUserId(userId);
			return RestResponse.build().withSuccess("TripPlanner Application Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(tripPlannerResponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed To Find Tripplanner Application due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Tripplanner Application due to" + e.getMessage());
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAllTripPlanner(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		LOGGER.info("Fetching All Active User");
		try {
			List<TripPlannerResponseModal> tripPlannerResponseModals = tripPlannerService.findAll(page, size);
			long totalRecords = tripPlannerService.countAllTripPlanner();
			return RestResponse.build().withSuccess("TripPlanner Application Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(tripPlannerResponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed To Find Tripplanner Application due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Tripplanner Application due to" + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/ApprovedByUserId", produces = "application/json")
	public RestResponse findApprovedByUserId(@RequestParam("userId") Integer userId) {
		LOGGER.info("Fetching Approved Trip Planner");
		try {
			TripPlannerResponseModal tripPlannerResponseModal = tripPlannerService.findApprovedByUserId(userId);
			return RestResponse.build().withSuccess("SuccessFully TripPlanner Found", tripPlannerResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Find Tripplanner Application due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Tripplanner Application due to" + e.getMessage());
		}
	}
	

}
