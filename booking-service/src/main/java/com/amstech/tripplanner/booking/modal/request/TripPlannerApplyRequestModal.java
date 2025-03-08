package com.amstech.tripplanner.booking.modal.request;


import lombok.Data;

@Data
public class TripPlannerApplyRequestModal {

	private int userId;
	private int experience;
	private String companyName;
	private String bio;
}
