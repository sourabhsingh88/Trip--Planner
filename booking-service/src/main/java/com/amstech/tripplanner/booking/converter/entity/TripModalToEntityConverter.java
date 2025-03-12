package com.amstech.tripplanner.booking.converter.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Accommodation;
import com.amstech.tripplanner.booking.entity.Activity;
import com.amstech.tripplanner.booking.entity.City;
import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.Meal;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Transport;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.TripPlanner;
import com.amstech.tripplanner.booking.modal.request.AccommodationCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.ActivityCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.MealCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.TransportCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.TripCreateRequestModal;
import com.amstech.tripplanner.booking.repo.CityRepo;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripPlannerRepo;

@Component
public class TripModalToEntityConverter {

	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private TripPlannerRepo tripPlannerRepo;
	@Autowired
	private StatusRepo statusRepo;
	
	private final int countinueStatusId  = 9;
	private final int discontinueStatusId = 10;

	public Location tripCreate(TripCreateRequestModal tripCreateRequestModal) throws Exception {
		Optional<City> cityOptional = cityRepo.findById(tripCreateRequestModal.getCityId());
		if(!cityOptional.isPresent()) {
			throw new Exception("City Is not Available with id : " + tripCreateRequestModal.getCityId());
		}
		
		Optional<TripPlanner> tripPlannerOptional = tripPlannerRepo.findById(tripCreateRequestModal.getTripplannerId());
		if(!tripPlannerOptional.isPresent()) {
			throw new Exception("TripPlanner Is not Available with id : " + tripCreateRequestModal.getTripplannerId());
		}
		
		Optional<Status> statusOptional = statusRepo.findById(countinueStatusId);
		if(!statusOptional.isPresent()) {
			throw new Exception("Staus Is not Available with id : " + countinueStatusId);
		}
		
		Location location =new Location();
		location.setName(tripCreateRequestModal.getLocationName());
		location.setCity(cityOptional.get());
		
		Trip trip =  new Trip();
		trip.setTripPlanner(tripPlannerOptional.get());
		trip.setStatus(statusOptional.get());
		trip.setName(tripCreateRequestModal.getName());
		trip.setDescription(tripCreateRequestModal.getDescription());
		trip.setCreatedAt(new Date());
		trip.setDuration(tripCreateRequestModal.getDuration());
		trip.setEndDate(tripCreateRequestModal.getEndDtate());
		trip.setStartDate(tripCreateRequestModal.getStartDate());
		trip.setToLocation(tripCreateRequestModal.getTo());
		trip.setFromLocation(tripCreateRequestModal.getFrom());
		trip.setTourGuideName(tripCreateRequestModal.getTourGuideName());
		trip.setPrice(tripCreateRequestModal.getPrrice());
		trip.setImgUrl(tripCreateRequestModal.getUrl());
		
		List<Accommodation> accommodations = new ArrayList<>();
		for (AccommodationCreateRequestModal accommodationCreateRequestModal : tripCreateRequestModal.getAccommodationCreateRequestModals()) {
			Accommodation accommodation = new Accommodation();
			accommodation.setCapacity(accommodationCreateRequestModal.getCapacity());
			accommodation.setDescription(accommodationCreateRequestModal.getDescription());
			accommodation.setEmail(accommodationCreateRequestModal.getEmail());
			accommodation.setName(accommodationCreateRequestModal.getName());
			accommodation.setPhoneNumber(accommodationCreateRequestModal.getPhoneNumber());
			accommodation.setTypes(accommodationCreateRequestModal.getType());
			accommodation.setTrip(trip);
			accommodations.add(accommodation);
		}
		trip.setAccommodations(accommodations);
		
		List<Activity> activities = new ArrayList<>();
		for (ActivityCreateRequestModal activityCreateRequestModal : tripCreateRequestModal.getActivityCreateRequestModals()) {
			Activity activity = new Activity();
			activity.setDescription(activityCreateRequestModal.getDescription());
			activity.setName(activityCreateRequestModal.getName());
			activity.setActivityDate(activityCreateRequestModal.getActivityDate());
			activity.setTrip(trip);
			activities.add(activity);
		}
		trip.setActivities(activities);
		
		List<Meal> meals = new ArrayList<>();
		for (MealCreateRequestModal mealCreateRequestModal : tripCreateRequestModal.getMealCreateRequestModals()) {
			Meal meal = new Meal();
			meal.setDescription(mealCreateRequestModal.getDescription());
			meal.setName(mealCreateRequestModal.getName());
			meal.setTrip(trip);
			meals.add(meal);
		}
		trip.setMeals(meals);
		
		List<Transport> transports = new ArrayList<>();
		for (TransportCreateRequestModal transportCreateRequestModal : tripCreateRequestModal.getTransportCreateRequestModals()) {
			Transport transport = new Transport();
			transport.setDescription(transportCreateRequestModal.getDescription());
			transport.setName(transportCreateRequestModal.getName());
			transport.setTrip(trip);
			transports.add(transport);
		}
		trip.setTransports(transports);
		
		trip.setLocation(location);
		location.setTrips(List.of(trip));
		
		return location;
		
	}
}
