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

import com.amstech.tripplanner.booking.modal.request.NotificationCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.response.NotificationResponseModal;
import com.amstech.tripplanner.booking.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	private NotificationService notificationService;

	public NotificationController() {
		LOGGER.info("NotificationController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/create",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> create(@RequestBody NotificationCreateRequestModal notificationCreateRequestModal){
		
		LOGGER.info("Notification Create with tital : {} " ,notificationCreateRequestModal.getTitle());
		try {
			notificationService.create(notificationCreateRequestModal);
			return new ResponseEntity<Object>("Notification Create SuccessFully ",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("failed to Create Notification due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("failed to Create Notification due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value ="/mark-read", produces = "application/json")
	public ResponseEntity<Object> markRead(@RequestParam("id") Integer id){
		
		LOGGER.info("Notification Start Marking As Read with id : {} " , id);
		try {
			notificationService.markRead(id);
			return new ResponseEntity<Object>("Notification Mark As Read SuccessFully ",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("failed to  Mark As Read Notification due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("failed to  Mark As Read Notification due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/byreceiver", produces = "application/json")
	public ResponseEntity<Object> findAllByReceiver(@RequestParam("receiverId") Integer receiverId){
		
		LOGGER.info("Fetching Notification for User with id : {} " , receiverId);
		try {
			List<NotificationResponseModal> notificationResponseModals = notificationService.findAllForReciver(receiverId);
			return new ResponseEntity<Object>(notificationResponseModals,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("failed to  Fetch Notification for User with id : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("failed to  Fetch Notification for User with id  : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
