package com.amstech.tripplanner.booking.modal.response;

import lombok.Data;

@Data
public class TripResponseModal {
	private int id;
	private String name;
	private String to;
	private String from;
	private double price;
	private String imgURL;
	private String description;
	
}
