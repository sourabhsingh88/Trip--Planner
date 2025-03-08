package com.amstech.tripplanner.booking.service;

import java.util.Date;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amstech.tripplanner.booking.converter.modal.UserEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.User;

import com.amstech.tripplanner.booking.modal.request.*;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;
import com.amstech.tripplanner.booking.repo.LocationRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Service
public class Userservice {

	private Logger LOGGER = LoggerFactory.getLogger(Userservice.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LocationRepo locationRepo;

	@Autowired
	private UserEntityToModalConverter userEntityToModalConverter;

	public void update(UserUpdateRequestModel1 updaterequestModel1) throws Exception {
		Optional<User> userOptional = userRepo.findById(updaterequestModel1.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updaterequestModel1.getId());
		}
		Optional<Location> locationOptional = locationRepo.findById(updaterequestModel1.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("Location Is Not Avilable with id :" + updaterequestModel1.getLocationId());
		}
		User user = userOptional.get();

		user.setGender(updaterequestModel1.getGender());
		user.setName(updaterequestModel1.getName());
		user.setLocation(locationOptional.get());
		user.setDob(updaterequestModel1.getDob());
		user.setUpdatedAt(new Date());

		userRepo.save(user);
	}

	public void signup(UserSignUpRequestModel signupRequestModel) throws Exception {

		Optional<Location> locationOptional = locationRepo.findById(signupRequestModel.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("Location Is Not Avilable with id :" + signupRequestModel.getLocationId());
		}
		User user = new User();

		user.setLocation(locationOptional.get());
		user.setName(signupRequestModel.getName());
		user.setEmail(signupRequestModel.getEmail());
		user.setPhoneNumber(signupRequestModel.getPhoneNumber());
		user.setGender(signupRequestModel.getGender());
		user.setPassword(signupRequestModel.getPassword());
		user.setDob(signupRequestModel.getDob());
		userRepo.save(user);
	}

	public void softDeletedId(Integer id) throws Exception {
		Optional<User> userOptional = userRepo.findById(id);
		if (userOptional.isEmpty()) {
			throw new Exception("user not found");
		}
		User user = userOptional.get();
		if (user.getIsDeleted() == 1) {
			throw new Exception("User already deactivated");
		}
		user.setIsDeleted(1);
		userRepo.save(user);
	}

	public UserResponseModal findById(Integer id) throws Exception {
		Optional<User> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			throw new Exception("User is Not Found with Id : " + id);
		}
		User user = userOptional.get();
		if (user.getIsDeleted() == 1) {
			throw new Exception(user.getName() + " you account is currentlly decative so you can't see the detaile");
		}
		UserResponseModal userResponseModal = userEntityToModalConverter.findById(user);

		return userResponseModal;
	}
	public UserResponseModal login(UserLoginRequestModal userLoginRequestModal) throws Exception {

		User userName = userRepo.findByUserName(userLoginRequestModal.getUserName());
		if (userName == null) {
			throw new Exception("User Is not Exit with username : " + userLoginRequestModal.getUserName());
		}
		User userlogin = userRepo.login(userLoginRequestModal.getUserName(), userLoginRequestModal.getPassword());
		if (userlogin == null) {
			throw new Exception("Password are Invalid ");
		}

		if (userlogin.getIsDeleted() == 1) {
			throw new Exception(userlogin.getName() + " you account is currentlly ddecative so you can't Login");
		}

		UserResponseModal userResponseModal = userEntityToModalConverter.findById(userlogin);
		return userResponseModal;
	}

	public void updateEmail(UserEmailUpdateModal updateEmailUpdateModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(updateEmailUpdateModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updateEmailUpdateModal.getId());
		}
		User user = userOptional.get();
		user.setEmail(updateEmailUpdateModal.getEmail());
		user.setUpdatedAt(new Date());
		userRepo.save(user);
	}
	public void updatePhoneNumber(UserUpdatePhoneNumber updatePhoneNumberModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(updatePhoneNumberModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updatePhoneNumberModal.getId());
		}
		User user = userOptional.get();
		user.setPhoneNumber(updatePhoneNumberModal.getPhoneNumber());
		user.setUpdatedAt(new Date());
		userRepo.save(user);
	}
}
