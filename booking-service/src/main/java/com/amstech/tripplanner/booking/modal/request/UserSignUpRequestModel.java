package com.amstech.tripplanner.booking.modal.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserSignUpRequestModel {

	private int locationId;
	private String name;
	private String email;
	private String phoneNumber;
	private String gender;
	private String password;
	private Date dob;

}
