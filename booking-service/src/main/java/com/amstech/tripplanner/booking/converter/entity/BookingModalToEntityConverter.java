package com.amstech.tripplanner.booking.converter.entity;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Booking;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.BookingCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.BookingUpdateRequestModal;
import com.amstech.tripplanner.booking.repo.BookingRepo;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Component
public class BookingModalToEntityConverter {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TripRepo tripRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private BookingRepo bookingRepo;
	private int requestSentId = 11;
	public Booking create(BookingCreateRequestModal bookingCreateRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(bookingCreateRequestModal.getUserId());
		if (!userOptional.isPresent()) {
			throw new Exception("Seneder Is no Available with id  : " + bookingCreateRequestModal.getUserId());
		}
		Optional<Trip> tripOptional = tripRepo.findById(bookingCreateRequestModal.getTripId());
		if (!tripOptional.isPresent()) {
			throw new Exception("Trip Is no Available with id  : " + bookingCreateRequestModal.getTripId());
		}
		Optional<Status> statusOptional = statusRepo.findById(requestSentId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + requestSentId);
		}
		Booking booking =new Booking();
		booking.setUser(userOptional.get());
		booking.setTrip(tripOptional.get());
		booking.setStatus(statusOptional.get());
		booking.setBookAt(new Date());
		booking.setUpdatedAt(new Date());
		return booking;
	}
	public Booking updatStatus(BookingUpdateRequestModal bookingUpdateRequestModal) throws Exception {
		Optional<Booking> bookingOptional = bookingRepo.findById(bookingUpdateRequestModal.getId());
		if (!bookingOptional.isPresent()) {
			throw new Exception("Booking Is no Available with id  : " + bookingUpdateRequestModal.getId());
		}
		Optional<Status> statusOptional = statusRepo.findById(bookingUpdateRequestModal.getStatusId());
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + bookingUpdateRequestModal.getStatusId());
		}

		if (bookingOptional.get().getStatus().getId() == statusOptional.get().getId()) {
			throw new Exception("Booking Is Already in " + statusOptional.get().getName() + " Status.");
		}
		
		Booking booking = bookingOptional.get();
		booking.setStatus(statusOptional.get());
		booking.setUpdatedAt(new Date());
		return booking;
	}
}
