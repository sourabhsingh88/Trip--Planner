package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Accommodation;
import com.amstech.tripplanner.booking.entity.Activity;
import com.amstech.tripplanner.booking.entity.Location;
import com.amstech.tripplanner.booking.entity.Meal;
import com.amstech.tripplanner.booking.entity.Transport;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.entity.UserRole;
import com.amstech.tripplanner.booking.modal.response.AccommodationResponseModal;
import com.amstech.tripplanner.booking.modal.response.ActivityResponseModal;
import com.amstech.tripplanner.booking.modal.response.LocationWithTripResponseModal;
import com.amstech.tripplanner.booking.modal.response.LocationWithUserResponseModal;
import com.amstech.tripplanner.booking.modal.response.MealResponseModal;
import com.amstech.tripplanner.booking.modal.response.RoleResponseModal;
import com.amstech.tripplanner.booking.modal.response.TransportResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;

@Component
public class LocationEntityToModalConverter {

	public LocationWithUserResponseModal findById(Location location) {
		LocationWithUserResponseModal locationWithUserResponseModal = new LocationWithUserResponseModal();
		locationWithUserResponseModal.setId(location.getId());
		locationWithUserResponseModal.setName(location.getName());
		locationWithUserResponseModal.setCityName(location.getCity().getName());
		locationWithUserResponseModal.setStateName(location.getCity().getState().getName());
		locationWithUserResponseModal.setCountryName(location.getCity().getState().getCountry());
		List<UserResponseModal> userResponseModals = new ArrayList<>();
		for (User user : location.getUsers()) {
			UserResponseModal userResponseModal = new UserResponseModal();
			userResponseModal.setId(user.getId());
			userResponseModal.setName(user.getName());
			userResponseModal.setPhoneNumber(user.getPhoneNumber());
			userResponseModal.setEmail(user.getEmail());
			userResponseModal.setGender(user.getGender());
			userResponseModal.setDob(user.getDob());
			userResponseModal.setIsDeleted(user.getIsDeleted());
			
			List<RoleResponseModal> roleResponseModals = new ArrayList<>();
			for (UserRole userRole : user.getUserRoles()) {
				RoleResponseModal roleResponseModal = new RoleResponseModal();
				roleResponseModal.setId(userRole.getRole().getId());
				roleResponseModal.setName(userRole.getRole().getName());
				roleResponseModals.add(roleResponseModal);
			}
			userResponseModal.setRoles(roleResponseModals);
			userResponseModals.add(userResponseModal);
		}
		locationWithUserResponseModal.setUserList(userResponseModals);
		return locationWithUserResponseModal;
		
	}
	public LocationWithTripResponseModal findBy(Location location) {
		LocationWithTripResponseModal locationWithTripResponseModal = new LocationWithTripResponseModal();
		locationWithTripResponseModal.setId(location.getId());
		locationWithTripResponseModal.setName(location.getName());
		locationWithTripResponseModal.setCityName(location.getCity().getName());
		locationWithTripResponseModal.setStateName(location.getCity().getState().getName());
		locationWithTripResponseModal.setCountryName(location.getCity().getState().getCountry());
		List<TripDetailResponseModal> tripDetailResponseModals = new ArrayList<>();
		for (Trip trip : location.getTrips()) {
			TripDetailResponseModal tripDetailResponseModal = new TripDetailResponseModal();
			tripDetailResponseModal.setId(trip.getId());
			tripDetailResponseModal.setName(trip.getName());
			tripDetailResponseModal.setDescription(trip.getDescription());
			tripDetailResponseModal.setFrom(trip.getFromLocation());
			tripDetailResponseModal.setTo(trip.getToLocation());
			tripDetailResponseModal.setPrice(trip.getPrice());
			tripDetailResponseModal.setImgURL(trip.getImgUrl());
			tripDetailResponseModal.setTripPlannerName(trip.getTripPlanner().getUser().getName());
			tripDetailResponseModal.setCountryName(trip.getLocation().getCity().getState().getCountry());
			tripDetailResponseModal.setStateName(trip.getLocation().getCity().getState().getName());
			tripDetailResponseModal.setCityName(trip.getLocation().getCity().getName());
			tripDetailResponseModal.setLocationName(trip.getLocation().getName());
			tripDetailResponseModal.setDurationInDays(trip.getDuration());
			tripDetailResponseModal.setStartDate(trip.getStartDate());
			tripDetailResponseModal.setEndDate(trip.getEndDate());
			tripDetailResponseModal.setStatusName(trip.getStatus().getName());
			
			
			List<AccommodationResponseModal> accommodationResponseModals = new ArrayList<>();
			for (Accommodation accommodation : trip.getAccommodations()) {
				AccommodationResponseModal accommodationResponseModal = new AccommodationResponseModal();
				accommodationResponseModal.setId(accommodation.getId());
				accommodationResponseModal.setName(accommodation.getName());
				accommodationResponseModal.setEmail(accommodation.getEmail());
				accommodationResponseModal.setPhoneNumber(accommodation.getPhoneNumber());
				accommodationResponseModal.setTypes(accommodation.getTypes());
				accommodationResponseModal.setCapacity(accommodation.getCapacity());
				accommodationResponseModal.setDescription(accommodation.getDescription());
				
				accommodationResponseModals.add(accommodationResponseModal);
			}
			tripDetailResponseModal.setAccommodationsResponseModals(accommodationResponseModals);
			
			List<ActivityResponseModal> activityResponseModals = new ArrayList<>();
			for (Activity activity : trip.getActivities()) {
				ActivityResponseModal activityResponseModal = new ActivityResponseModal();
				activityResponseModal.setId(activity.getId());
				activityResponseModal.setName(activity.getName());
				activityResponseModal.setActivityDate(activity.getActivityDate());
				activityResponseModal.setDescription(activity.getDescription());
				
				activityResponseModals.add(activityResponseModal);
			}
			tripDetailResponseModal.setActivitiesResponseModals(activityResponseModals);
			
			List<MealResponseModal> mealResponseModals = new ArrayList<>();
			for (Meal meal : trip.getMeals()) {
				MealResponseModal mealResponseModal = new MealResponseModal();
				mealResponseModal.setId(meal.getId());
				mealResponseModal.setName(meal.getName());
				mealResponseModal.setDescription(meal.getDescription());
				
				mealResponseModals.add(mealResponseModal);
			}
			tripDetailResponseModal.setMealsResponseModals(mealResponseModals);
			
			List<TransportResponseModal> transportResponseModals = new ArrayList<>();
			for (Transport transport : trip.getTransports()) {
				TransportResponseModal transportResponseModal = new TransportResponseModal();
				transportResponseModal.setId(transport.getId());
				transportResponseModal.setName(transport.getName());
				transportResponseModal.setDescription(transport.getDescription());
				
				transportResponseModals.add(transportResponseModal);
			}
			tripDetailResponseModal.setTransportsResponseModals(transportResponseModals);;
			
			tripDetailResponseModals.add(tripDetailResponseModal);
		}
		locationWithTripResponseModal.setTripDetailResponseModals(tripDetailResponseModals);
		return locationWithTripResponseModal;
	}
}
