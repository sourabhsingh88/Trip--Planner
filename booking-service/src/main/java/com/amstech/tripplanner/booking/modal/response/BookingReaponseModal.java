package com.amstech.tripplanner.booking.modal.response;

import java.util.List;

import com.amstech.tripplanner.booking.entity.Accommodation;

public class BookingReaponseModal {

	private int id;
	private String tripName;
	private String tripPlannerName;
	private String tripDescription;
	private double tripPrice;
	private String statusName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public String getTripPlannerName() {
		return tripPlannerName;
	}
	public void setTripPlannerName(String tripPlannerName) {
		this.tripPlannerName = tripPlannerName;
	}
	public String getTripDescription() {
		return tripDescription;
	}
	public void setTripDescription(String tripDescription) {
		this.tripDescription = tripDescription;
	}
	public double getTripPrice() {
		return tripPrice;
	}
	public void setTripPrice(double tripPrice) {
		this.tripPrice = tripPrice;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
