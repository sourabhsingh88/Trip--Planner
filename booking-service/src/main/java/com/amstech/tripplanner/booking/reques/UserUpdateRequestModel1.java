package com.amstech.tripplanner.booking.reques;

import java.util.Date;

public class UserUpdateRequestModel1 {
private int id;
private int locationId;
private String name;
private String gender;
private Date dob;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getLocationId() {
	return locationId;
}
public void setLocationId(int locationId) {
	this.locationId = locationId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}



}
