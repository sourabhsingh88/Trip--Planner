package com.amstech.tripplanner.booking.converter.entity;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.request.UserUpdateEmailRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdatePasswordRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdatePhoneNumberRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdateRequestModel;

@Component
public class UserModelToEntityConverter {

	public User signup(UserSignUpRequestModel userSignUpRequestModel, Optional<Location> locationOptional) {
		User user = new User();

		user.setLocation(locationOptional.get());
		user.setName(userSignUpRequestModel.getName());
		user.setEmail(userSignUpRequestModel.getEmail());
		user.setPhoneNumber(userSignUpRequestModel.getPhoneNumber());
		user.setGender(userSignUpRequestModel.getGender());
		user.setPassword(userSignUpRequestModel.getPassword());
		user.setDob(userSignUpRequestModel.getDob());
		
		return user;
	}
	
	public User update(UserUpdateRequestModel updateRequestModel,Optional<User> userOptional, Optional<Location> locationOptional) {
		User user = userOptional.get();

		user.setGender(updateRequestModel.getGender());
		user.setName(updateRequestModel.getName());
		user.setLocation(locationOptional.get());
		user.setDob(updateRequestModel.getDob());
		user.setUpdatedAt(new Date());
		return user;
	}
	
	public User updateEmail(UserUpdateEmailRequestModal userUpdateEmailRequestModal,Optional<User> userOptional) {
		User user = userOptional.get();
		user.setEmail(userUpdateEmailRequestModal.getEmail());
		user.setUpdatedAt(new Date());
		return user;
	}
	
	public User updatePhoneNumber(UserUpdatePhoneNumberRequestModal userUpdatePhoneNumberRequestModal,Optional<User> userOptional) {
		User user = userOptional.get();
		user.setPhoneNumber(userUpdatePhoneNumberRequestModal.getPhoneNumber());
		user.setUpdatedAt(new Date());
		return user;
	}
	public User updatePassword(UserUpdatePasswordRequestModal userUpdatePasswordRequestModal,Optional<User> userOptional) {
		User user = userOptional.get();
		user.setPassword(userUpdatePasswordRequestModal.getPassword());
		user.setUpdatedAt(new Date());
		return user;
	}
		
	
	
}
