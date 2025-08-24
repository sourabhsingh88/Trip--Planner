package com.amstech.tripplanner.booking.modal.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserFullUpdateRequestModal {
	
	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private String gender;
	private Date dob;
	private String locationName;
	private int cityId;
	private String profieImage;
}
