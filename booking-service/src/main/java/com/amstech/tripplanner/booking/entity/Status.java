package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="status")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="status")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Trip
	@OneToMany(mappedBy="status")
	private List<Trip> trips;

	//bi-directional many-to-one association to TripPlanner
	@OneToMany(mappedBy="status")
	private List<TripPlanner> tripPlanners;

	public Status() {
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

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setStatus(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setStatus(null);

		return booking;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setStatus(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setStatus(null);

		return notification;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setStatus(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setStatus(null);

		return trip;
	}

	public List<TripPlanner> getTripPlanners() {
		return this.tripPlanners;
	}

	public void setTripPlanners(List<TripPlanner> tripPlanners) {
		this.tripPlanners = tripPlanners;
	}

	public TripPlanner addTripPlanner(TripPlanner tripPlanner) {
		getTripPlanners().add(tripPlanner);
		tripPlanner.setStatus(this);

		return tripPlanner;
	}

	public TripPlanner removeTripPlanner(TripPlanner tripPlanner) {
		getTripPlanners().remove(tripPlanner);
		tripPlanner.setStatus(null);

		return tripPlanner;
	}

}