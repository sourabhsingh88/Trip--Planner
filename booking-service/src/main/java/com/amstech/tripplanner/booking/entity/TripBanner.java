package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the trip_banner database table.
 * 
 */
@Entity
@Table(name="trip_banner")
@NamedQuery(name="TripBanner.findAll", query="SELECT t FROM TripBanner t")
public class TripBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String url;

	//bi-directional many-to-one association to Trip
	@ManyToOne
	private Trip trip;

	public TripBanner() {
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}