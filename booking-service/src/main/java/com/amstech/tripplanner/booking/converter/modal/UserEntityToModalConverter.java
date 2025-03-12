package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.response.RoleResponseModal;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;

@Component
public class UserEntityToModalConverter {
	public UserResponseModal findById(User user) {

		UserResponseModal userResponseModal = new UserResponseModal();
		userResponseModal.setId(user.getId());
		userResponseModal.setName(user.getName());
		userResponseModal.setDob(user.getDob());
		userResponseModal.setEmail(user.getEmail());
		userResponseModal.setGender(user.getGender());
		userResponseModal.setPhoneNumber(user.getPhoneNumber());
		userResponseModal.setIsDeleted(user.getIsDeleted());
		
		List<RoleResponseModal> roleResponseModals = new ArrayList<>();
		for (UserRole userRole : user.getUserRoles()) {
			RoleResponseModal roleResponseModal = new RoleResponseModal();
			roleResponseModal.setId(userRole.getRole().getId());
			roleResponseModal.setName(userRole.getRole().getName());
			roleResponseModals.add(roleResponseModal);
		}
		userResponseModal.setRoles(roleResponseModals);
		
		return userResponseModal;
	}

	public List<UserResponseModal> findAll(List<User> users) {
		List<UserResponseModal> userResponseModals = new ArrayList<>();
		for (User user : users) {
			UserResponseModal userResponseModal = new UserResponseModal();
			userResponseModal.setId(user.getId());
			userResponseModal.setName(user.getName());
			userResponseModal.setDob(user.getDob());
			userResponseModal.setEmail(user.getEmail());
			userResponseModal.setGender(user.getGender());
			userResponseModal.setPhoneNumber(user.getPhoneNumber());
			userResponseModal.setIsDeleted(user.getIsDeleted());
			
			userResponseModals.add(userResponseModal);
		}
		return userResponseModals;
	}
}
