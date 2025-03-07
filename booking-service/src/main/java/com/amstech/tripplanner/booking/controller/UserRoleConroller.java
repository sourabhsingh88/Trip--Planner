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
import com.amstech.tripplanner.booking.modal.request.UserRoleAssigneRequestModal;
import com.amstech.tripplanner.booking.service.UserRoleService;

@RestController
@RequestMapping("user-role")
public class UserRoleConroller {
	
	private final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private UserRoleService userRoleService;

	public UserRoleConroller() {
		LOGGER.info("UserRoleConroller : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/assigne",consumes = "application/json")
	public ResponseEntity<Object> assigneRole(@RequestBody UserRoleAssigneRequestModal userRoleAssigneRequestModal){
		
		LOGGER.info("Start Assinging Role to User whose id  : {} " ,userRoleAssigneRequestModal.getUserId());
		try {
			int id = userRoleService.assigneRole(userRoleAssigneRequestModal);
			return new ResponseEntity<Object>("Successfully Assigne Role to user and userRole id is : " + id ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Assigne Role to user due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Assigne Role to user due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
