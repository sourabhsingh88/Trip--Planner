package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mobile_otp database table.
 * 
 */
@Entity
@Table(name="mobile_otp")
@NamedQuery(name="MobileOtp.findAll", query="SELECT m FROM MobileOtp m")
public class MobileOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="genrated_at")
	private Date genratedAt;

	@Column(name="genrated_otp")
	private int genratedOtp;

	@Column(name="mobile_number")
	private String mobileNumber;

	public MobileOtp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getGenratedAt() {
		return this.genratedAt;
	}

	public void setGenratedAt(Date genratedAt) {
		this.genratedAt = genratedAt;
	}

	public int getGenratedOtp() {
		return this.genratedOtp;
	}

	public void setGenratedOtp(int genratedOtp) {
		this.genratedOtp = genratedOtp;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}