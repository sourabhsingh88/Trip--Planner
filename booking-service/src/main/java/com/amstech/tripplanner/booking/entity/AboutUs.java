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

	@Column(name="img_url")
	private String imgUrl;

	private String title;

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

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}