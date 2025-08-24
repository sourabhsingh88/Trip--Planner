package com.amstech.tripplanner.booking.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Lob
	private String message;

	private String title;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="receiver_role_id")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="sender_id")
	private User sender;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private User receiver;

	//bi-directional many-to-one association to Trip
	@ManyToOne
	private Trip trip;

	//bi-directional many-to-one association to Status
	@ManyToOne
	private Status status;

	public Notification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getSender() {
		return this.sender;
	}

	public void setSender(User user1) {
		this.sender = user1;
	}

	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(User user2) {
		this.receiver = user2;
	}

	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}