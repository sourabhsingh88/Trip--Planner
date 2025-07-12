package com.amstech.tripplanner.booking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.constants.RoleEnum;
import com.amstech.tripplanner.booking.converter.entity.BookingModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.BookingEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Booking;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.TripPlanner;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.BookingCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.BookingUpdateRequestModal;
import com.amstech.tripplanner.booking.modal.request.NotificationCreateRequestModal;
import com.amstech.tripplanner.booking.modal.response.BookingReaponseModal;
import com.amstech.tripplanner.booking.repo.BookingRepo;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripPlannerRepo;
import com.amstech.tripplanner.booking.repo.TripRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Service
public class BookingService {

	private final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

	@Autowired private UserRepo userRepo;
	@Autowired private TripPlannerRepo tripPlannerRepo;
	@Autowired private TripRepo tripRepo;
	@Autowired private StatusRepo statusRepo;
	@Autowired private BookingRepo bookingRepo;
	@Autowired private BookingEntityToModalConverter bookingEntityToModalConverter;
	@Autowired private BookingModalToEntityConverter bookingModalToEntityConverter;
	@Autowired private NotificationSenderService notificationSenderService;

	private final int requestSentId = 11;

	public BookingService() {
		LOGGER.debug("BookingService : Object Created");
	}

	// ------------------- CREATE -------------------
	public BookingReaponseModal create(BookingCreateRequestModal bookingCreateRequestModal) throws Exception {
		Optional<Trip> tripOptional = tripRepo.findById(bookingCreateRequestModal.getTripId());
		if (!tripOptional.isPresent()) {
			throw new Exception("Trip not available with ID: " + bookingCreateRequestModal.getTripId());
		}

		List<Booking> bookingExits = bookingRepo.findByUserIdTripId(
				bookingCreateRequestModal.getUserId(),
				bookingCreateRequestModal.getTripId());

		if (bookingExits != null) {
			for (Booking bookingExit : bookingExits) {
				if (bookingExit.getStatus().getId() == requestSentId)
					throw new Exception("Booking already exists with status Request Sent (id=" + bookingExit.getId() + ")");
			}
		}

		Booking booking = bookingModalToEntityConverter.create(bookingCreateRequestModal);
		Booking savedBooking = bookingRepo.save(booking);

		NotificationCreateRequestModal notification = buildNotificationForBookingStatus(savedBooking, null);
		notificationSenderService.send(notification);

		return bookingEntityToModalConverter.findById(savedBooking);
	}

	// ------------------- UPDATE STATUS -------------------
	public BookingReaponseModal updateStatus(BookingUpdateRequestModal bookingUpdateRequestModal) throws Exception {
		Optional<Booking> bookingOptional = bookingRepo.findById(bookingUpdateRequestModal.getId());
		if (!bookingOptional.isPresent()) {
			throw new Exception("Booking not available with id: " + bookingUpdateRequestModal.getId());
		}

		Optional<Status> statusOptional = statusRepo.findById(bookingUpdateRequestModal.getStatusId());
		if (!statusOptional.isPresent()) {
			throw new Exception("Status not available with id: " + bookingUpdateRequestModal.getStatusId());
		}

		if (bookingOptional.get().getStatus().getId() == statusOptional.get().getId()) {
			throw new Exception("Booking is already in status: " + statusOptional.get().getName());
		}

		Booking booking = bookingModalToEntityConverter.updatStatus(bookingUpdateRequestModal);
		Booking updatedBooking = bookingRepo.save(booking);

		NotificationCreateRequestModal notification = buildNotificationForBookingStatus(updatedBooking, bookingUpdateRequestModal.getRejectedBy());
		notificationSenderService.send(notification);

		return bookingEntityToModalConverter.findById(updatedBooking);
	}

	// ------------------- FIND BY ID -------------------
	public BookingReaponseModal findById(Integer id) throws Exception {
		Optional<Booking> bookingOptional = bookingRepo.findById(id);
		if (!bookingOptional.isPresent()) {
			throw new Exception("Booking not available with id: " + id);
		}
		return bookingEntityToModalConverter.findById(bookingOptional.get());
	}

	// ------------------- FIND BY USER -------------------
	public List<BookingReaponseModal> findByUserId(Integer userId, Integer page, Integer size) throws Exception {
		if (!userRepo.findById(userId).isPresent()) {
			throw new Exception("User not found with id: " + userId);
		}
		List<Booking> bookings = bookingRepo.findByUserId(userId, PageRequest.of(page, size));
		if (bookings.isEmpty()) {
			throw new Exception("No bookings found for userId: " + userId);
		}
		return bookingEntityToModalConverter.findByUserId(bookings);
	}

	public long countByUserId(Integer userId) {
		return bookingRepo.countByUserId(userId);
	}

	// ------------------- FIND BY TRIP PLANNER -------------------
	public List<BookingReaponseModal> findByTripplannerId(Integer tripplannerId, Integer page, Integer size) throws Exception {
		if (!tripPlannerRepo.findById(tripplannerId).isPresent()) {
			throw new Exception("TripPlanner not found with id: " + tripplannerId);
		}
		List<Booking> bookings = bookingRepo.findByTripplannerId(tripplannerId, PageRequest.of(page, size));
		if (bookings.isEmpty()) {
			throw new Exception("No bookings found for tripPlannerId: " + tripplannerId);
		}
		return bookingEntityToModalConverter.findByUserId(bookings);
	}

	public long countByTripplanner(Integer tripplannerId) {
		return bookingRepo.countByTripplannerId(tripplannerId);
	}

	// ------------------- FIND ALL -------------------
	public List<BookingReaponseModal> findAll(Integer page, Integer size) throws Exception {
		List<Booking> bookings = bookingRepo.findAllBooking(PageRequest.of(page, size));
		if (bookings.isEmpty()) {
			throw new Exception("No bookings found.");
		}
		return bookingEntityToModalConverter.findByUserId(bookings);
	}

	public long countAllBooking() {
		return bookingRepo.countAllBooking();
	}

	// ------------------- BUILD NOTIFICATION -------------------
	private NotificationCreateRequestModal buildNotificationForBookingStatus(Booking booking, String rejectedBy) throws Exception {
		NotificationCreateRequestModal notification = new NotificationCreateRequestModal();

		Integer statusId = booking.getStatus().getId();
		Trip trip = booking.getTrip();
		User tripPlanner = trip.getTripPlanner().getUser();
		User user = booking.getUser();

		switch (statusId) {
			case 11: // Request Sent
				notification.setSenderId(user.getId());
				notification.setReceiverId(tripPlanner.getId());
				notification.setReceiverRoleId(RoleEnum.TRIP_PLANNER);
				notification.setTitle("New Booking Request");
				notification.setMessage(user.getName() + " sent a booking request for trip: " + trip.getName());
				break;

			case 12: // Response Received
				notification.setSenderId(tripPlanner.getId());
				notification.setReceiverId(user.getId());
				notification.setReceiverRoleId(RoleEnum.USER);
				notification.setTitle("Trip Planner Responded");
				notification.setMessage("Trip planner responded to your booking request for trip: " + trip.getName());
				break;

			case 13: // Payment Pending
				notification.setSenderId(user.getId());
				notification.setReceiverId(tripPlanner.getId());
				notification.setReceiverRoleId(RoleEnum.TRIP_PLANNER);
				notification.setTitle("Payment Initiated");
				notification.setMessage("User made payment for trip '" + trip.getName() + "', please confirm.");
				break;

			case 14: // Confirmed
				notification.setSenderId(tripPlanner.getId());
				notification.setReceiverId(user.getId());
				notification.setReceiverRoleId(RoleEnum.USER);
				notification.setTitle("Booking Confirmed");
				notification.setMessage("Your booking for trip '" + trip.getName() + "' is confirmed.");
				break;

			case 15: // Rejected
				if ("USER".equalsIgnoreCase(rejectedBy)) {
					notification.setSenderId(user.getId());
					notification.setReceiverId(tripPlanner.getId());
					notification.setReceiverRoleId(RoleEnum.TRIP_PLANNER);
					notification.setTitle("Booking Rejected by User");
					notification.setMessage(user.getName() + " has rejected the booking for trip: " + trip.getName());
				} else {
					notification.setSenderId(tripPlanner.getId());
					notification.setReceiverId(user.getId());
					notification.setReceiverRoleId(RoleEnum.USER);
					notification.setTitle("Booking Rejected");
					notification.setMessage("Your booking for trip '" + trip.getName() + "' has been rejected by the trip planner.");
				}
				break;

			default:
				throw new Exception("Unhandled booking status: " + statusId);
		}

		notification.setTripId(trip.getId());
		return notification;
	}
}
