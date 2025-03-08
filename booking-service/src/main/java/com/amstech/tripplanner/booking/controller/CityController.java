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

import com.amstech.tripplanner.booking.modal.response.CityResponseModal;

import com.amstech.tripplanner.booking.service.CityService;
@RestController
@RequestMapping("city")
public class CityController {

	private final Logger LOGGER = LoggerFactory.getLogger(CityController.class);
	@Autowired
	private CityService cityService;

	public CityController() {
		LOGGER.info("CityController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/all",produces = "application/json")
	public ResponseEntity<Object> findAll(@RequestParam("stateId") Integer stateId){
		
		LOGGER.info("Fetching All City with stateId  : " + stateId );
		try {
			List<CityResponseModal> cityResponseModals = cityService.findAllByStateId(stateId);
			return new ResponseEntity<Object>(cityResponseModals,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to find City due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to find City  due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
