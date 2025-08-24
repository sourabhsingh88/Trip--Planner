package com.amstech.tripplanner.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amstech.tripplanner.booking.modal.response.StateResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.StateService;

@RestController
@RequestMapping("state")
public class StateController {

	private final Logger LOGGER = LoggerFactory.getLogger(StateController.class);
	
	@Autowired
	private StateService stateService;

	public StateController() {
		LOGGER.info("StateController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/all",produces = "application/json")
	public RestResponse findAll(){
		
		LOGGER.info("Fetching All States ");
		try {
			List<StateResponseModal> stateResponseModals = stateService.findAll();
			return RestResponse.build().withSuccess("All State Founds").withData(stateResponseModals);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to find States due to : {}", e.getMessage());
			return RestResponse.build().withError("ailed to find States  due to" + e.getMessage());
		}
	}

}
