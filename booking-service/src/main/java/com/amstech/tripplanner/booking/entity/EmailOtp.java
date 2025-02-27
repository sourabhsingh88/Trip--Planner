package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the email_otp database table.
 * 
 */
@Entity
@Table(name="email_otp")
@NamedQuery(name="EmailOtp.findAll", query="SELECT e FROM EmailOtp e")
public class EmailOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name="genrated_at")
	private String genratedAt;

	@Column(name="genrated_otp")
	private int genratedOtp;

	public EmailOtp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenratedAt() {
		return this.genratedAt;
	}

	public void setGenratedAt(String genratedAt) {
		this.genratedAt = genratedAt;
	}

	public int getGenratedOtp() {
		return this.genratedOtp;
	}

	public void setGenratedOtp(int genratedOtp) {
		this.genratedOtp = genratedOtp;
	}

}