package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class UserResponseModal {
	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private String gender;
	private Date dob;
	private String profileImage;
	private int isDeleted;
	private int locationId;
	private String LocationName;
	private int cityId;
	private String cityName;
	private int sateId;
	private String stateName;
	private List<RoleResponseModal> roles;

}
