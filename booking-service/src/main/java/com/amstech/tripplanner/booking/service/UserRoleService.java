package com.amstech.tripplanner.booking.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.controller.BookingController;
import com.amstech.tripplanner.booking.converter.entity.UserRoleModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.UserRoleEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Role;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.request.UserRoleAssigneRequestModal;
import com.amstech.tripplanner.booking.modal.response.UserRoleResponseModal;
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
	@Autowired
	private UserRoleEntityToModalConverter userRoleEntityToModalConverter;
	@Autowired
	private UserRoleModalToEntityConverter userRoleModalToEntityConverter;

	public UserRoleService() {
		LOGGER.debug("UserRoleService : Object Created");
	}

	public UserRoleResponseModal assigneRole(UserRoleAssigneRequestModal userRoleAssigneRequestModal) throws Exception {

		UserRole userRoleExist = userRoleRepo.findByUserIdRoleId(userRoleAssigneRequestModal.getUserId(),
				userRoleAssigneRequestModal.getRoleId());
		if (userRoleExist != null) {
			throw new Exception("That user Already Assigne  with roleId : " + userRoleAssigneRequestModal.getRoleId());
		}
		UserRole userRole = userRoleModalToEntityConverter.assigneRole(userRoleAssigneRequestModal);
		UserRole saveUserRole = userRoleRepo.save(userRole);
		return userRoleEntityToModalConverter.findById(saveUserRole);

	}

	public List<UserRoleResponseModal> findByRoleId(Integer roleId, Integer page, Integer size) throws Exception {
		Optional<Role> roleOptional = roleRepo.findById(roleId);
		if (!roleOptional.isPresent()) {
			throw new Exception("Role is not Available With id :  " + roleId);
		}

		List<UserRole> userRoles = userRoleRepo.findByRoleId(roleId, PageRequest.of(page, size));
		if (userRoles.isEmpty()) {
			throw new Exception("Not User Found For This Role :" + roleOptional.get().getName());
		}
		return userRoleEntityToModalConverter.findAll(userRoles);
	}

	public long countByRoleId(Integer roleId) {
		return userRoleRepo.countByRoleId(roleId);
	}

	public List<UserRoleResponseModal> findByUserId(Integer userId, Integer page, Integer size) throws Exception {
		Optional<User> userOptional = userRepo.findById(userId);
		if (!userOptional.isPresent()) {
			throw new Exception("User is not Available With id :  " + userId);
		}
		List<UserRole> userRoles = userRoleRepo.findByUserId(userId, PageRequest.of(page, size));
		if (userRoles.isEmpty()) {
			throw new Exception("Not Roles Found For This User :" + userOptional.get().getName());
		}
		return userRoleEntityToModalConverter.findAll(userRoles);
	}

	public long countByUserId(Integer userId) {
		return userRoleRepo.countByUserId(userId);
	}

	public List<UserRoleResponseModal> findAll(Integer page, Integer size) throws Exception {

		List<UserRole> userRoles = userRoleRepo.findAllUserRole(PageRequest.of(page, size));
		if (userRoles.isEmpty()) {
			throw new Exception("Not User Roles Found");
		}
		return userRoleEntityToModalConverter.findAll(userRoles);
	}

	public long countAllUserRole() {
		return userRoleRepo.countAllUserRole();
	}

}
