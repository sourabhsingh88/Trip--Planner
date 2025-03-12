package com.amstech.tripplanner.booking.modal.response;

import lombok.Data;

@Data
public class AccommodationResponseModal {
	private int id;
	private String name;
	private String types;
	private int capacity;
	private String phoneNumber;
	private String email;
	private String description;
}
