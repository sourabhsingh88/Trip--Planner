package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;
import java.util.List;

import com.amstech.tripplanner.booking.entity.Accommodation;
import com.amstech.tripplanner.booking.entity.Activity;
import com.amstech.tripplanner.booking.entity.Meal;
import com.amstech.tripplanner.booking.entity.Transport;
import com.amstech.tripplanner.booking.entity.TripBanner;

public class TripDetailResponseModal {

	private int id ;
	private String locationName;
	private String cityName;
	private String stateName;
	private String countryName;
	private String statusName;
	private String tripPlannerName;
	private String name;
	private String to;
	private String from;
	private int durationInDays;
	private double price;
	private String imgURL;
	private Date startDate;
	private Date endDate;
	private String description;
	private List<Accommodation> accommodations;
	private List<Activity> activities;
	private List<Meal> meals;
	private List<Transport> transports;
	private List<TripBanner> tripBanners;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getTripPlannerName() {
		return tripPlannerName;
	}
	public void setTripPlannerName(String tripPlannerName) {
		this.tripPlannerName = tripPlannerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public int getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Accommodation> getAccommodations() {
		return accommodations;
	}
	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public List<Meal> getMeals() {
		return meals;
	}
	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	public List<Transport> getTransports() {
		return transports;
	}
	public void setTransports(List<Transport> transports) {
		this.transports = transports;
	}
	public List<TripBanner> getTripBanners() {
		return tripBanners;
	}
	public void setTripBanners(List<TripBanner> tripBanners) {
		this.tripBanners = tripBanners;
	}
}
