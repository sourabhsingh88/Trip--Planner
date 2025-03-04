package com.amstech.tripplanner.booking.modal.request;

import java.util.Date;

public class UserSignUpRequestModel {

private int locationId;
private String name;
private String email;
private String phoneNumber;
private String gender;
private String password;
private Date dob;
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
