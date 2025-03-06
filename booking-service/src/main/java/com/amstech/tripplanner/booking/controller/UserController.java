package com.amstech.tripplanner.booking.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amstech.tripplanner.booking.modal.request.UserLoginRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.request.UserUpdateRequestModel1;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;
import com.amstech.tripplanner.booking.service.Userservice;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private Userservice userservice;

	public UserController() {
		LOGGER.info("User Controller : object Created ");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signUp", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> singup(@RequestBody UserSignUpRequestModel userSignUpRequestModel) {
		LOGGER.info("Start Creating User Account with email : {} ", userSignUpRequestModel.getEmail());
		try {
			userservice.signup(userSignUpRequestModel);
			return new ResponseEntity<>("Data save success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> update(@RequestBody UserUpdateRequestModel1 userUpdateRequestModel1) {
		LOGGER.info("Updating user detail with id: {} ", userUpdateRequestModel1.getId());
		try {
			userservice.update(userUpdateRequestModel1);
			return new ResponseEntity<>("Update success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Update Failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json",produces = "application/json")
	public ResponseEntity<Object> login(@RequestBody UserLoginRequestModal userLoginRequestModal){
		System.out.println("start login with username : " + userLoginRequestModal.getUserName());
		try {
			UserResponseModal userResponseModal = userservice.login(userLoginRequestModal);
			return new ResponseEntity<Object>(userResponseModal,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("failed to login user due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byid", produces = "application/json")
	public ResponseEntity<Object> findById(@RequestParam("id") Integer id) {
		System.out.println("start Finding user details with id : " + id);
		try {
			UserResponseModal userResponseModal = userservice.findById(id);
			return new ResponseEntity<Object>(userResponseModal, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("failed to find user due to : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/softDelete")
	public ResponseEntity<Object> softDelete(@RequestParam("id") Integer id) {
		LOGGER.info("Start Deleting user detail with id: {} ", id);
		try {
			userservice.softDeletedId(id);
			return new ResponseEntity<>("Delete user successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete user due to " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
