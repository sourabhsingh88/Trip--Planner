package com.amstech.tripplanner.booking.converter.entity;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Role;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.request.UserRoleAssigneRequestModal;
import com.amstech.tripplanner.booking.repo.RoleRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Component
public class UserRoleModalToEntityConverter {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;

	public UserRole assigneRole( UserRoleAssigneRequestModal userRoleAssigneRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(userRoleAssigneRequestModal.getUserId());
		if(!userOptional.isPresent()) {
			throw new Exception("User Is not Avaliable with Id : " + userRoleAssigneRequestModal.getUserId());
		}
		Optional<Role> roleOptional = roleRepo.findById(userRoleAssigneRequestModal.getRoleId());
		if(!roleOptional.isPresent()) {
			throw new Exception("Role Is not Avaliable with Id : " + userRoleAssigneRequestModal.getRoleId());
		}
		
		UserRole userRole = new UserRole();
		
		userRole.setRole(roleOptional.get());
		userRole.setUser(userOptional.get());
		userRole.setCreatedAt(new Date());
		userRole.setUpdatedAt(new Date());
		return userRole;
	}
}
