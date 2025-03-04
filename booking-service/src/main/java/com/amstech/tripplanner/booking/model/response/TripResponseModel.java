package com.amstech.tripplanner.booking.model.response;

import java.time.DateTimeException;
import java.util.Date;

public class TripResponseModel {

	private int id;
	private String name;
	private String toLocation;
	private String fromLocation;
	private double price;
	private String imgUrl;
	private Date startDate;
	private Date endDate;
	private int tripId;
	private int locationId ;
	private int trip_planner_id ;
	private String tourGuideName ;
	private String description ;
	private DateTimeException createdAt ;
	private int duration ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getTrip_planner_id() {
		return trip_planner_id;
	}
	public void setTrip_planner_id(int trip_planner_id) {
		this.trip_planner_id = trip_planner_id;
	}
	public String getTourGuideName() {
		return tourGuideName;
	}
	public void setTourGuideName(String tourGuideName) {
		this.tourGuideName = tourGuideName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DateTimeException getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(DateTimeException createdAt) {
		this.createdAt = createdAt;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	

}
