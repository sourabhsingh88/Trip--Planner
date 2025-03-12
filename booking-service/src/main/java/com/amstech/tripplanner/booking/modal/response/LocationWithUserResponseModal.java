package com.amstech.tripplanner.booking.modal.response;

import java.util.List;

import lombok.Data;

@Data
public class LocationWithUserResponseModal {

	private int id;
	private String name;
	private String cityName;
	private String stateName;
	private String countryName;
	private List<UserResponseModal> userList;
}
