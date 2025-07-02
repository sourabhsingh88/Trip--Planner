package com.amstech.tripplanner.booking.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.converter.entity.TripPlannerModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.TripPlannerEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.TripPlanner;
import com.amstech.tripplanner.booking.modal.request.TripPlannerApplyRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripPlannerUpdateRequestModel;
import com.amstech.tripplanner.booking.modal.response.TripPlannerResponseModal;
import com.amstech.tripplanner.booking.repo.TripPlannerRepo;

@Service
public class TripPlannerService {

	private final Logger LOGGER = LoggerFactory.getLogger(TripPlannerService.class);

	@Autowired
	private TripPlannerRepo tripPlannerRepo;
	@Autowired
	private TripPlannerModalToEntityConverter tripPlannerModalToEntityConverter;
	@Autowired
	private TripPlannerEntityToModalConverter tripPlannerEntityToModalConverter;
	

	public TripPlannerService() {
		LOGGER.debug("TripPlannerService : Object Created");
	}

	public TripPlannerResponseModal apply(TripPlannerApplyRequestModal tripPlannerApplyRequestModal) throws Exception {
		TripPlanner tripPlanner = tripPlannerModalToEntityConverter.apply(tripPlannerApplyRequestModal);	
		TripPlanner saveTripPlanner = tripPlannerRepo.save(tripPlanner);
		return tripPlannerEntityToModalConverter.findById(saveTripPlanner);
	}
	public TripPlannerResponseModal updateStatus(TripPlannerUpdateRequestModel tripPlannerUpdateRequestModel) throws Exception {
		TripPlanner tripPlanner = tripPlannerModalToEntityConverter.updateStatus(tripPlannerUpdateRequestModel);
		TripPlanner updatedTripPlanner = tripPlannerRepo.save(tripPlanner);
		return tripPlannerEntityToModalConverter.findById(updatedTripPlanner);
	}
	public TripPlannerResponseModal findById(Integer id) throws Exception {
		Optional<TripPlanner> tripPlannerOptional = tripPlannerRepo.findById(id);
		if(!tripPlannerOptional.isPresent()) {
			throw new Exception("No Application Exist With Id" + id);
		}
		TripPlanner tripPlanner = tripPlannerOptional.get();
		return tripPlannerEntityToModalConverter.findById(tripPlanner);
	}
	public List<TripPlannerResponseModal> findAll(Integer page, Integer size) throws Exception {
		List<TripPlanner> tripPlanners = tripPlannerRepo.findAllTripPlanner(PageRequest.of(page, size));
		if(tripPlanners.isEmpty()) {
			throw new Exception("No Application tripplanner exist");
		}
		return tripPlannerEntityToModalConverter.findAll(tripPlanners);
	}
	public long countAllTripPlanner() throws Exception {
		return tripPlannerRepo.countAllTripPlanner();
	}
	
	public List<TripPlannerResponseModal> findAllByUserId(Integer userId,Integer page, Integer size) throws Exception {
		List<TripPlanner> tripPlanners = tripPlannerRepo.findAllByUserId(userId,PageRequest.of(page, size));
		if(tripPlanners.isEmpty()) {
			throw new Exception("No Application tripplanner exist");
		}
		return tripPlannerEntityToModalConverter.findAll(tripPlanners);
	}
	public long countAllByUserId(Integer userId) throws Exception {
		return tripPlannerRepo.countAllByUserId(userId);
	}
	
	public TripPlannerResponseModal findApprovedByUserId(Integer userId) throws Exception {
		TripPlanner tripPlanner = tripPlannerRepo.findApprovedByUserId(userId);
		if(tripPlanner == null) {
			throw new Exception("No Application tripplanner exist");
		}
		return tripPlannerEntityToModalConverter.findById(tripPlanner);
	}
	
	
}
