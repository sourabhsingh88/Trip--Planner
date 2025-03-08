package com.amstech.tripplanner.booking.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.controller.BookingController;
import com.amstech.tripplanner.booking.entity.Role;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.request.UserRoleAssigneRequestModal;
import com.amstech.tripplanner.booking.repo.RoleRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;
import com.amstech.tripplanner.booking.repo.UserRoleRepo;

@Service
public class UserRoleService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserRoleService.class);

	@Autowired
	private UserRoleRepo userRoleRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;

	public UserRoleService() {
		LOGGER.debug("UserRoleService : Object Created");
	}

	public int assigneRole(UserRoleAssigneRequestModal userRoleAssigneRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(userRoleAssigneRequestModal.getUserId());
		if (!userOptional.isPresent()) {
			throw new Exception("User is not Available With id :  " + userRoleAssigneRequestModal.getUserId());
		}
		Optional<Role> roleOptional = roleRepo.findById(userRoleAssigneRequestModal.getRoleId());
		if (!userOptional.isPresent()) {
			throw new Exception("Role is not Available With id :  " + userRoleAssigneRequestModal.getRoleId());
		}

		UserRole userRoleExist = userRoleRepo.findByUserIdRoleId(userOptional.get().getId(),
				roleOptional.get().getId());
		if (userRoleExist != null) {
			throw new Exception("That user Already Assigne  with role : " + roleOptional.get().getName());
		}

		UserRole userRole = new UserRole();
		userRole.setRole(roleOptional.get());
		userRole.setUser(userOptional.get());
		userRole.setCreatedAt(new Date());
		userRole.setUpdatedAt(new Date());
		
		UserRole saveUserRole = userRoleRepo.save(userRole);
		return saveUserRole.getId();
		
	}

}
