package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the about_us database table.
 * 
 */
@Entity
@Table(name="about_us")
@NamedQuery(name="AboutUs.findAll", query="SELECT a FROM AboutUs a")
public class AboutUs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String email;

	@Column(name="img_url")
	private String imgUrl;

	@Lob
	private String mission;

	@Column(name="phone_number")
	private String phoneNumber;

	private String title;

	@Lob
	private String vision;

	public AboutUs() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMission() {
		return this.mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVision() {
		return this.vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

}