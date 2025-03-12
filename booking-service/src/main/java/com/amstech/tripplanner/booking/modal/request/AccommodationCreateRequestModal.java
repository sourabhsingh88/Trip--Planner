package com.amstech.tripplanner.booking.modal.request;

import lombok.Data;

@Data
public class AccommodationCreateRequestModal {

	private String name;
	private String type;
	private int capacity;
	private String phoneNumber;
	private String email;
	private String description;
}
