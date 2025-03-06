package com.amstech.tripplanner.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.tripplanner.booking.reques.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.reques.UserUpdateRequestModel1;
import com.amstech.tripplanner.booking.service.Userservice;
import com.asmstech.tripplanner.booking.response.UserFindAllResponsemodel;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Userservice userservice;

	public UserController() {
		System.out.println("User Controller : object Created ");
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes ="application/json", produces = "application/json")
	public ResponseEntity<Object> update(@RequestBody UserUpdateRequestModel1 userUpdateRequestModel1) {
		System.out.println("updating user detail with id:"  + userUpdateRequestModel1.getId());
		try {
			userservice.update(userUpdateRequestModel1);
			return new ResponseEntity<Object>("Update success" , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Update Failed :" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	
		@RequestMapping(method = RequestMethod.POST, value ="/signUp",consumes = "application/json", produces = "application/json")
		public ResponseEntity<Object> singup(@RequestBody UserSignUpRequestModel userSignUpRequestModel){
			
			
			try {
				userservice.signup(userSignUpRequestModel);
				return new ResponseEntity<Object>("Data save success",HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			  return new ResponseEntity<Object>("failed to save",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			@RequestMapping(method = RequestMethod.DELETE, value ="/softDelete")
			public ResponseEntity<Object> softDelete(@RequestParam("id") Integer id){
				try {
					userservice.softDeletedId(id);
					return new ResponseEntity<Object>("delete user successfully",HttpStatus.OK);
				} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>("failed to delete user due to "+ e.getMessage(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			@RequestMapping(method = RequestMethod.POST, value ="/findAll",consumes = "application/json", produces = "application/json")
			public ResponseEntity<Object> singup(@RequestBody UserFindAllResponsemodel findAllResponsemodel){
				
				
				try {
					userservice.findAll(findAllResponsemodel);
					return new ResponseEntity<Object>("Data save success",HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
				  return new ResponseEntity<Object>("failed to save",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		
	
	}

//	@RequestMapping(method = RequestMethod.POST, value = "/signUp", consumes = "application/json", produces = "application/json")
//
//	public ResponseEntity<Object> signUp(@RequestBody String body) {
//		System.out.println("signUp" + body);
//		return new ResponseEntity<Object>(body, HttpStatus.OK);
//
//	}


