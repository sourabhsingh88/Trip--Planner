package com.amstech.tripplanner.booking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.converter.entity.UserModelToEntityConverter;
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
	private UserModelToEntityConverter userModelToEntityConverter;

	@Autowired
	private UserEntityToModalConverter userEntityToModalConverter;

	public Userservice() {
		LOGGER.debug("Userservice : Object Created");
	}

	public UserResponseModal signup(UserSignUpRequestModel signupRequestModel) throws Exception {
		Optional<Location> locationOptional = locationRepo.findById(signupRequestModel.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("Location Is Not Avilable with id :" + signupRequestModel.getLocationId());
		}
		User userByemail = userRepo.findByemail(signupRequestModel.getEmail());
		if (userByemail != null) {
			throw new Exception("User Is Already Exist with is Email : " + signupRequestModel.getEmail());
		}
		User userByPhoneMunber = userRepo.findByPhonenumber(signupRequestModel.getPhoneNumber());
		if (userByPhoneMunber != null) {
			throw new Exception("User Is Already Exist with is PhoneNumber : " + signupRequestModel.getPhoneNumber());
		}
		User user = userModelToEntityConverter.signup(signupRequestModel, locationOptional);
		User savedUser = userRepo.save(user);
		return userEntityToModalConverter.findById(savedUser);
	}

	public UserResponseModal update(UserUpdateRequestModel updateRequestModel) throws Exception {

		Optional<User> userOptional = userRepo.findById(updateRequestModel.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updateRequestModel.getId());
		}

		Optional<Location> locationOptional = locationRepo.findById(updateRequestModel.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("Location Is Not Avilable with id :" + updateRequestModel.getLocationId());
		}
		User user = userModelToEntityConverter.update(updateRequestModel, userOptional, locationOptional);
		User updateUser = userRepo.save(user);
		return userEntityToModalConverter.findById(updateUser);
	}

	public void softDeletedId(Integer id) throws Exception {
		Optional<User> userOptional = userRepo.findById(id);
		if (userOptional.isEmpty()) {
			throw new Exception("User is Not Found with Id : " + id);
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

	public UserResponseModal updateEmail(UserUpdateEmailRequestModal updateEmailUpdateModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(updateEmailUpdateModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updateEmailUpdateModal.getId());
		}
		User userByemail = userRepo.findByemail(updateEmailUpdateModal.getEmail());
		if (userByemail != null) {
			throw new Exception("User Is Already Exist with is Email : " + updateEmailUpdateModal.getEmail());
		}
		User user = userModelToEntityConverter.updateEmail(updateEmailUpdateModal, userOptional);
		User updatedUser = userRepo.save(user);
		return userEntityToModalConverter.findById(updatedUser);
	}

	public UserResponseModal updatePhoneNumber(UserUpdatePhoneNumberRequestModal updatePhoneNumberModal)
			throws Exception {
		Optional<User> userOptional = userRepo.findById(updatePhoneNumberModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updatePhoneNumberModal.getId());
		}
		User userByPhoneMunber = userRepo.findByPhonenumber(updatePhoneNumberModal.getPhoneNumber());
		if (userByPhoneMunber != null) {
			throw new Exception("User Is Already Exist with is PhoneNumber : " + updatePhoneNumberModal.getPhoneNumber());
		}
		User user = userModelToEntityConverter.updatePhoneNumber(updatePhoneNumberModal, userOptional);
		User updatedUser = userRepo.save(user);
		return userEntityToModalConverter.findById(updatedUser);
	}

	public UserResponseModal updatePassword(UserUpdatePasswordRequestModal updatepasswordModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(updatepasswordModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updatepasswordModal.getId());
		}
		User user = userModelToEntityConverter.updatePassword(updatepasswordModal, userOptional);
		User updatedUser = userRepo.save(user);
		return userEntityToModalConverter.findById(updatedUser);
	}

	public List<UserResponseModal> findAllActive(Integer page, Integer size) throws Exception {
		List<User> allActiveUser = userRepo.findAllActiveUser(PageRequest.of(page, size));
		if (allActiveUser.isEmpty()) {
			throw new Exception("No Active User Available");
		}
		return userEntityToModalConverter.findAll(allActiveUser);
	}

	public long countAllActive() throws Exception {
		long countActiveUser = userRepo.countAllActiveUser();
		if (countActiveUser == 0) {
			throw new Exception("No Active User Available");
		}
		return countActiveUser;
	}

	public List<UserResponseModal> findAllDeactive(Integer page, Integer size) throws Exception {
		List<User> allDeactiveUser = userRepo.findAllDeactiveUser(PageRequest.of(page, size));
		if (allDeactiveUser.isEmpty()) {
			throw new Exception("No Deactive User Available");
		}
		return userEntityToModalConverter.findAll(allDeactiveUser);
	}
	
	public long countAllDeactive() throws Exception {
		long countActiveUser = userRepo.countAllDeactiveUser();
		if (countActiveUser == 0) {
			throw new Exception("No Deactive User Available");
		}
		return countActiveUser;
	}
}
