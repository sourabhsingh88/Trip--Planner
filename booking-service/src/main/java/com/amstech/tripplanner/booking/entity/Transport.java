package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


/**
 * The persistent class for the transport database table.
 * 
 */
@Entity
@NamedQuery(name="Transport.findAll", query="SELECT t FROM Transport t")
public class Transport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Trip
	@ManyToOne
	private Trip trip;

	public Transport() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}