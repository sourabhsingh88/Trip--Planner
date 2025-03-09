package com.amstech.tripplanner.booking.converter.modal;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;

@Component
public class UserEntityToModalConverter {
	public UserResponseModal findById(User user) {
		UserResponseModal userResponseModal = new UserResponseModal();
		userResponseModal.setId(user.getId());
		userResponseModal.setCountryName(user.getLocation().getCity().getState().getCountry());
		userResponseModal.setStateName(user.getLocation().getCity().getState().getName());
		userResponseModal.setCityName(user.getLocation().getCity().getName());
		userResponseModal.setLocationId(user.getLocation().getId());
		userResponseModal.setName(user.getName());
		userResponseModal.setDob(user.getDob());
		userResponseModal.setEmail(user.getEmail());
		userResponseModal.setGender(user.getGender());
		userResponseModal.setPhoneNumber(user.getPhoneNumber());
		
		return userResponseModal;
	}
}
