package com.amstech.tripplanner.booking.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amstech.tripplanner.booking.modal.request.UserUpdateEmailRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserLoginRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.request.UserUpdatePasswordRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdatePhoneNumberRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdateRequestModel;
import com.amstech.tripplanner.booking.modal.response.LocationWithUserResponseModal;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.FileService;
import com.amstech.tripplanner.booking.service.Userservice;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private Userservice userservice;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileService fileService;

	public UserController() {
		LOGGER.info("User Controller : object Created ");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signUp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public RestResponse singup(@RequestParam("userRequestModalJson") String userRequestModalJson,@RequestParam("profilePhoto") MultipartFile profilePhoto) throws IOException {
		String filePath = null;

		try {

			UserSignUpRequestModel userSignUpRequestModel = objectMapper.readValue(userRequestModalJson,UserSignUpRequestModel.class);
			LOGGER.info("Start Creating User Account with email : {} ", userSignUpRequestModel.getEmail());
			
			if(profilePhoto != null) {
				LOGGER.info("File Name : "+ profilePhoto.getOriginalFilename() + " and Size : " + profilePhoto.getSize() );
				if(profilePhoto.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				filePath = fileService.saveFile(profilePhoto.getBytes(),"user",FilenameUtils.getExtension(profilePhoto.getOriginalFilename()));
			}
			
			userSignUpRequestModel.setProfieImage(filePath);
			
			LocationWithUserResponseModal locationWithUserResponseModal = userservice.signup(userSignUpRequestModel);
			LOGGER.info("User Response Modal Reseived");
			return RestResponse.build().withSuccess("User Account Created Successfully", locationWithUserResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			LOGGER.error("Failed to save user due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to save user due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	public RestResponse update(@RequestBody UserUpdateRequestModel userUpdateRequestModel1) {
		LOGGER.info("Updating user detail with id: {} ", userUpdateRequestModel1.getId());
		try {
			UserResponseModal userResponseModal = userservice.update(userUpdateRequestModel1);
			LOGGER.info("User Response Modal Reseived");
			return RestResponse.build().withSuccess("User Success Fully Update the Basic Details", userResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Update User Basic Details due to: {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Update User Basic Details due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json", produces = "application/json")
	public RestResponse login(@RequestBody UserLoginRequestModal userLoginRequestModal) {
		LOGGER.info("start login with username : {} ", userLoginRequestModal.getUserName());
		try {
			UserResponseModal userResponseModal = userservice.login(userLoginRequestModal);
			LOGGER.info("User Response Modal Reseived");
			return RestResponse.build().withSuccess("User Login Successfull", userResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Login due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Login due to  : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId", produces = "application/json")
	public RestResponse findById(@RequestParam("id") Integer id) {
		LOGGER.info("start Finding user details with id : {}", id);
		try {
			UserResponseModal userResponseModal = userservice.findById(id);
			return RestResponse.build().withSuccess("SuccessFully User Found", userResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Find User due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withSuccess("Failed To Find User due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/softDelete")
	public RestResponse softDelete(@RequestParam("id") Integer id) {
		LOGGER.info("Start Deleting user detail with id: {} ", id);
		try {
			userservice.softDeletedId(id);
			return RestResponse.build().withSuccess("Successfully Deactivate thre user");
		} catch (Exception e) {
			LOGGER.error("Failed To Deactivate User due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Deactivate the User");
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateEmail", consumes = "application/json", produces = "application/json")
	public RestResponse updateEmail(@RequestBody UserUpdateEmailRequestModal updateEmailUpdateModal) {
		LOGGER.info("Updating user detail with id: {} ", updateEmailUpdateModal.getId());
		try {
			UserResponseModal userResponseModal = userservice.updateEmail(updateEmailUpdateModal);
			return RestResponse.build().withSuccess("Successfully Update the Email", userResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Update Email due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Update Email due to" + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePhoneNumber", consumes = "application/json", produces = "application/json")
	public RestResponse updatePhoneNumber(@RequestBody UserUpdatePhoneNumberRequestModal updatePhoneNumberModal) {
		LOGGER.info("Updating user detail with id: {} ", updatePhoneNumberModal.getId());
		try {
			UserResponseModal userResponseModal = userservice.updatePhoneNumber(updatePhoneNumberModal);
			return RestResponse.build().withSuccess("Successfully Update the Phone Number", userResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Update Phone Number due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Update Phone Number due to" + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword", consumes = "application/json", produces = "application/json")
	public RestResponse updatePassword(@RequestBody UserUpdatePasswordRequestModal updatePasswordModal) {
		LOGGER.info("Updating user detail with id: {} ", updatePasswordModal.getId());
		try {
			UserResponseModal userResponseModal = userservice.updatePassword(updatePasswordModal);
			return RestResponse.build().withSuccess("Successfully Update the Password", userResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed To Update Password due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Update Password due to" + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allActiveUser", produces = "application/json")
	public RestResponse findAllActiveUser(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		LOGGER.info("Fetching All Active User");
		try {
			List<UserResponseModal> userResponseModals = userservice.findAllActive(page, size);
			long totalRecords = userservice.countAllActive();
			return RestResponse.build().withSuccess("Active User Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(userResponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed To Find Active User due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find Active User due to" + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allDeactiveUser", produces = "application/json")
	public RestResponse findAllDeactiveUser(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		LOGGER.info("Fetching All Active User");
		try {
			List<UserResponseModal> userResponseModals = userservice.findAllDeactive(page, size);
			long totalRecords = userservice.countAllDeactive();
			return RestResponse.build().withSuccess("Dective User Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(userResponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed To Find Deactive User due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find Deactive User due to" + e.getMessage());
		}
	}
}
