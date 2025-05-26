package com.amstech.tripplanner.booking.converter.entity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.City;
import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.Role;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.request.UserUpdateEmailRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdatePasswordRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdatePhoneNumberRequestModal;
import com.amstech.tripplanner.booking.modal.request.UserUpdateRequestModel;
import com.amstech.tripplanner.booking.repo.CityRepo;
import com.amstech.tripplanner.booking.repo.LocationRepo;
import com.amstech.tripplanner.booking.repo.RoleRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;
import com.amstech.tripplanner.booking.repo.UserRoleRepo;

@Component
public class UserModelToEntityConverter {

	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	private int customerId = 1;

	public Location signup(UserSignUpRequestModel userSignUpRequestModel) throws Exception {
		Optional<City> cityOptional = cityRepo.findById(userSignUpRequestModel.getCityId());
		if(!cityOptional.isPresent()) {
			throw new Exception("City Is not Available with id : " +userSignUpRequestModel.getCityId());
		}
		
		Optional<Role> roleOptional = roleRepo.findById(customerId);
		if(!roleOptional.isPresent()) {
			throw new Exception("Role Is Not Available With Id : " + customerId);
		}
		
		Location location = new Location();
		location.setName(userSignUpRequestModel.getLocationName());
		location.setCity(cityOptional.get());
		
		User user = new User();
		user.setName(userSignUpRequestModel.getName());
		user.setEmail(userSignUpRequestModel.getEmail());
		user.setPhoneNumber(userSignUpRequestModel.getPhoneNumber());
		user.setGender(userSignUpRequestModel.getGender());
		user.setPassword(userSignUpRequestModel.getPassword());
		user.setDob(userSignUpRequestModel.getDob());
		user.setProfileImage(userSignUpRequestModel.getProfieImage());
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		
		user.setLocation(location);
		location.setUsers(List.of(user));
		
		UserRole userRole = new UserRole();
		
		userRole.setRole(roleOptional.get());
		
		userRole.setUser(user);
		userRole.setCreatedAt(new Date());
		userRole.setUpdatedAt(new Date());
		user.setUserRoles(List.of(userRole));
		
		return location;
	}
	
	public User update(UserUpdateRequestModel updateRequestModel) throws Exception {
		Optional<User> userOptional = userRepo.findById(updateRequestModel.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updateRequestModel.getId());
		}

		Optional<Location> locationOptional = locationRepo.findById(updateRequestModel.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("Location Is Not Avilable with id :" + updateRequestModel.getLocationId());
		}
		User user = userOptional.get();
		user.setGender(updateRequestModel.getGender());
		user.setName(updateRequestModel.getName());
		user.setLocation(locationOptional.get());
		user.setDob(updateRequestModel.getDob());
		user.setUpdatedAt(new Date());
		return user;
	}
	
	public User updateEmail(UserUpdateEmailRequestModal userUpdateEmailRequestModal) throws Exception {
		
		Optional<User> userOptional = userRepo.findById(userUpdateEmailRequestModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + userUpdateEmailRequestModal.getId());
		}
		User user = userOptional.get();
		user.setEmail(userUpdateEmailRequestModal.getEmail());
		user.setUpdatedAt(new Date());
		return user;
	}
	
	public User updatePhoneNumber(UserUpdatePhoneNumberRequestModal userUpdatePhoneNumberRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(userUpdatePhoneNumberRequestModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + userUpdatePhoneNumberRequestModal.getId());
		}
		User user = userOptional.get();
		user.setPhoneNumber(userUpdatePhoneNumberRequestModal.getPhoneNumber());
		user.setUpdatedAt(new Date());
		return user;
	}
	public User updatePassword(UserUpdatePasswordRequestModal userUpdatePasswordRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(userUpdatePasswordRequestModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + userUpdatePasswordRequestModal.getId());
		}
		User user = userOptional.get();
		user.setPassword(userUpdatePasswordRequestModal.getPassword());
		user.setUpdatedAt(new Date());
		return user;
	}
		
	
	
}
