package com.amstech.tripplanner.booking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.converter.modal.CityEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.City;
import com.amstech.tripplanner.booking.entity.State;
import com.amstech.tripplanner.booking.modal.response.CityResponseModal;
import com.amstech.tripplanner.booking.repo.CityRepo;
import com.amstech.tripplanner.booking.repo.StateRepo;

@Service
public class CityService {

	private final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
	
	@Autowired
	private CityEntityToModalConverter cityEntityToModalConverter;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private StateRepo stateRepo;

	public CityService() {
		LOGGER.debug("CityService : Object Created");
	}
	
	public List<CityResponseModal> findAllByStateId(Integer stateId) throws Exception{
		Optional<State> stateOptional = stateRepo.findById(stateId);
		
		if(!stateOptional.isPresent()) {
			throw new Exception("State Is not available with id :"+ stateId);
		}
		
		List<City> cities = cityRepo.findAllByStateId(stateId);
		if(cities.isEmpty()) {
			throw new Exception("City Is not available with  Status ID :"+ stateId);
		}
		List<CityResponseModal> cityResponseModals = cityEntityToModalConverter.findAll(cities);
		return cityResponseModals;
	}
	
	
	
}
