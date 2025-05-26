package com.amstech.tripplanner.booking.security.model;

import java.util.List;

import lombok.Data;

@Data
public class TokenClaim {

	private Integer userId;
	private String firstName;
	private String lastName;
	private List<String> roleList;

}
