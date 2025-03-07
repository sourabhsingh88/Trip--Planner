package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;

import lombok.Data;

@Data
public class UserResponseModal {
	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private String gender;
	private String password;
	private Date dob;
	private int locationId;
	private String locationName;
	private String cityName;
	private String stateName;
	private String countryName;

}
