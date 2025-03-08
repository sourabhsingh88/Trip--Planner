package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;

import java.util.List;

import com.amstech.tripplanner.booking.entity.Accommodation;
import com.amstech.tripplanner.booking.entity.Activity;
import com.amstech.tripplanner.booking.entity.Meal;
import com.amstech.tripplanner.booking.entity.Transport;
import com.amstech.tripplanner.booking.entity.TripBanner;

import lombok.Data;

@Data
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
	
}
