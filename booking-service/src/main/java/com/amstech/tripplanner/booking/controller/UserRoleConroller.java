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

import com.amstech.tripplanner.booking.modal.request.UserRoleAssigneRequestModal;
import com.amstech.tripplanner.booking.modal.response.UserRoleResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.UserRoleService;

@RestController
@RequestMapping("user-role")
public class UserRoleConroller {

	private final Logger LOGGER = LoggerFactory.getLogger(UserRoleConroller.class);

	@Autowired
	private UserRoleService userRoleService;

	public UserRoleConroller() {
		LOGGER.info("UserRoleConroller : Object Created");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/assigne", consumes = "application/json")
	public RestResponse assigneRole(@RequestBody UserRoleAssigneRequestModal userRoleAssigneRequestModal) {

		LOGGER.info("Start Assinging Role to User whose id  : {} ", userRoleAssigneRequestModal.getUserId());
		try {
			UserRoleResponseModal userRoleResponseModal = userRoleService.assigneRole(userRoleAssigneRequestModal);
			return RestResponse.build().withSuccess("Successfully Assigne Role to user", userRoleResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Assigne Role to user due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Assigne Role to user due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byRole", produces = "application/json")
	public RestResponse findByRoleId(@RequestParam("roleId") Integer roleId,@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		LOGGER.info("Start Fetching User with roleId : {} ", roleId);
		try {
			 List<UserRoleResponseModal> userRoleResponseModals = userRoleService.findByRoleId(roleId, page, size);
			long totalRecords = userRoleService.countByRoleId(roleId);
			return RestResponse.build().withSuccess("User Roles Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(userRoleResponseModals);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching User by Role due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Fetching User by Role due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/byUser", produces = "application/json")
	public RestResponse findByUserId(@RequestParam("userId") Integer userId,@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		LOGGER.info("Start Fetching Role of UserId : {} ", userId);
		try {
			 List<UserRoleResponseModal> userRoleResponseModals = userRoleService.findByUserId(userId, page, size);
			long totalRecords = userRoleService.countByUserId(userId);
			return RestResponse.build().withSuccess("User Roles Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(userRoleResponseModals);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching Roles of user due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Fetching Roles of user due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		LOGGER.info("Start Fetching User Role");
		try {
			 List<UserRoleResponseModal> userRoleResponseModals = userRoleService.findAll(page, size);
			long totalRecords = userRoleService.countAllUserRole();
			return RestResponse.build().withSuccess("User Roles Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(userRoleResponseModals);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching  User Roles due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Fetching User Roles due to : " + e.getMessage());
		}
	}


}
