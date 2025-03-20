package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.response.UserRoleResponseModal;

@Component
public class UserRoleEntityToModalConverter {

	public UserRoleResponseModal findById(UserRole userRole) {
		UserRoleResponseModal userRoleResponseModal = new UserRoleResponseModal();
		userRoleResponseModal.setId(userRole.getId());
		userRoleResponseModal.setUserName(userRole.getUser().getName());
		userRoleResponseModal.setRoleName(userRole.getRole().getName());
		userRoleResponseModal.setCreatedAt(userRole.getCreatedAt());
		userRoleResponseModal.setUpdatedAt(userRole.getUpdatedAt());
		return userRoleResponseModal;
	}
	
	public List<UserRoleResponseModal> findAll(List<UserRole> userRoles){
		List<UserRoleResponseModal> userRoleResponseModals = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			UserRoleResponseModal userRoleResponseModal = new UserRoleResponseModal();
			userRoleResponseModal.setId(userRole.getId());
			userRoleResponseModal.setUserName(userRole.getUser().getName());
			userRoleResponseModal.setRoleName(userRole.getRole().getName());
			userRoleResponseModal.setCreatedAt(userRole.getCreatedAt());
			userRoleResponseModal.setUpdatedAt(userRole.getUpdatedAt());
			
			userRoleResponseModals.add(userRoleResponseModal);
		}
		return userRoleResponseModals;
	}
}
