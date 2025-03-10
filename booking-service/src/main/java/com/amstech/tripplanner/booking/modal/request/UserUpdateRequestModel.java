package com.amstech.tripplanner.booking.modal.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserUpdateRequestModel {
	private int id;
	private int locationId;
	private String name;
	private String gender;
	private Date dob;

}
