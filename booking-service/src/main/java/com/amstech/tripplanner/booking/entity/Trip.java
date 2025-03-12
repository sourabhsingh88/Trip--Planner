package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the trip database table.
 * 
 */
@Entity
@NamedQuery(name="Trip.findAll", query="SELECT t FROM Trip t")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Lob
	private String description;

	private int duration;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="from_location")
	private String fromLocation;

	@Column(name="img_url")
	private String imgUrl;

	private String name;

	private double price;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="to_location")
	private String toLocation;

	@Column(name="tour_guide_name")
	private String tourGuideName;

	//bi-directional many-to-one association to Accommodation
	@OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Accommodation> accommodations;

	//bi-directional many-to-one association to Activity
	@OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Activity> activities;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="trip")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="trip")
	private List<Feedback> feedbacks;

	//bi-directional many-to-one association to Meal
	@OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Meal> meals;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="trip")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Transport
	@OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transport> transports;

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	//bi-directional many-to-one association to Status
	@ManyToOne
	private Status status;

	//bi-directional many-to-one association to TripPlanner
	@ManyToOne
	@JoinColumn(name="trip_planner_id")
	private TripPlanner tripPlanner;

	//bi-directional many-to-one association to TripBanner
	@OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TripBanner> tripBanners;

	public Trip() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFromLocation() {
		return this.fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getToLocation() {
		return this.toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getTourGuideName() {
		return this.tourGuideName;
	}

	public void setTourGuideName(String tourGuideName) {
		this.tourGuideName = tourGuideName;
	}

	public List<Accommodation> getAccommodations() {
		return this.accommodations;
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

	public Accommodation addAccommodation(Accommodation accommodation) {
		getAccommodations().add(accommodation);
		accommodation.setTrip(this);

		return accommodation;
	}

	public Accommodation removeAccommodation(Accommodation accommodation) {
		getAccommodations().remove(accommodation);
		accommodation.setTrip(null);

		return accommodation;
	}

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Activity addActivity(Activity activity) {
		getActivities().add(activity);
		activity.setTrip(this);

		return activity;
	}

	public Activity removeActivity(Activity activity) {
		getActivities().remove(activity);
		activity.setTrip(null);

		return activity;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setTrip(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setTrip(null);

		return booking;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Feedback addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setTrip(this);

		return feedback;
	}

	public Feedback removeFeedback(Feedback feedback) {
		getFeedbacks().remove(feedback);
		feedback.setTrip(null);

		return feedback;
	}

	public List<Meal> getMeals() {
		return this.meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Meal addMeal(Meal meal) {
		getMeals().add(meal);
		meal.setTrip(this);

		return meal;
	}

	public Meal removeMeal(Meal meal) {
		getMeals().remove(meal);
		meal.setTrip(null);

		return meal;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setTrip(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setTrip(null);

		return notification;
	}

	public List<Transport> getTransports() {
		return this.transports;
	}

	public void setTransports(List<Transport> transports) {
		this.transports = transports;
	}

	public Transport addTransport(Transport transport) {
		getTransports().add(transport);
		transport.setTrip(this);

		return transport;
	}

	public Transport removeTransport(Transport transport) {
		getTransports().remove(transport);
		transport.setTrip(null);

		return transport;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TripPlanner getTripPlanner() {
		return this.tripPlanner;
	}

	public void setTripPlanner(TripPlanner tripPlanner) {
		this.tripPlanner = tripPlanner;
	}

	public List<TripBanner> getTripBanners() {
		return this.tripBanners;
	}

	public void setTripBanners(List<TripBanner> tripBanners) {
		this.tripBanners = tripBanners;
	}

	public TripBanner addTripBanner(TripBanner tripBanner) {
		getTripBanners().add(tripBanner);
		tripBanner.setTrip(this);

		return tripBanner;
	}

	public TripBanner removeTripBanner(TripBanner tripBanner) {
		getTripBanners().remove(tripBanner);
		tripBanner.setTrip(null);

		return tripBanner;
	}

}