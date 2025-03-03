package com.amstech.tripplanner.booking.controller;

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

import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.request.UserUpdateRequestModel1;
import com.amstech.tripplanner.booking.service.Userservice;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Userservice userservice;

	private Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	public UserController() {
		LOGGER.info("User Controller : object Created ");

	}

	@RequestMapping(method = RequestMethod.POST, value = "/signUp", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> singup(@RequestBody UserSignUpRequestModel userSignUpRequestModel) {

		LOGGER.info("Start Creating User Account with email : {} ", userSignUpRequestModel.getEmail());
		try {
			userservice.signup(userSignUpRequestModel);
			return new ResponseEntity<Object>("Data save success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> update(@RequestBody UserUpdateRequestModel1 userUpdateRequestModel1) {

		LOGGER.info("updating user detail with id: {} ", userUpdateRequestModel1.getId());
		try {
			userservice.update(userUpdateRequestModel1);
			return new ResponseEntity<Object>("Update success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Update Failed :" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/softDelete")
	public ResponseEntity<Object> softDelete(@RequestParam("id") Integer id) {
		LOGGER.info("Start Deleting user detail with id: {} ", id);
		try {
			userservice.softDeletedId(id);
			return new ResponseEntity<Object>("delete user successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("failed to delete user due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
