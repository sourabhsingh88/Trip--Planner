package com.amstech.tripplanner.booking.service;

import java.util.Date;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.repo.LocationRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;
import com.amstech.tripplanner.booking.reques.*;
import com.asmstech.tripplanner.booking.response.UserFindAllResponsemodel;

@Service
public class Userservice {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LocationRepo locationRepo;

	public void update(UserUpdateRequestModel1 updaterequestModel1) throws Exception {
		Optional<User> userOptional = userRepo.findById(updaterequestModel1.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + updaterequestModel1.getId());
		}
		Optional<Location> locationOptional = locationRepo.findById(updaterequestModel1.getId());
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
	
	public void findAll(UserFindAllResponsemodel findAllResponsemodel) throws Exception {
		Optional<Location>locationOptional = locationRepo.findById(findAllResponsemodel.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("Location Is Not Avilable with id :" + findAllResponsemodel.getLocationId());
		}
		User user = new User();
		user.setLocation(locationOptional.get());
		user.setName(findAllResponsemodel.getName());
		user.setEmail(findAllResponsemodel.getEmail());
		user.setPhoneNumber(findAllResponsemodel.getPhoneNumber());
		user.setGender(findAllResponsemodel.getGender());
		user.setPassword(findAllResponsemodel.getPassword());
		user.setDob(findAllResponsemodel.getDob());
		userRepo.save(user);
	
	}
}
