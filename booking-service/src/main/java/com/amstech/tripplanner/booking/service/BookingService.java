package com.amstech.tripplanner.booking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.controller.BookingController;
import com.amstech.tripplanner.booking.converter.entity.BookingModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.BookingEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Booking;
import com.amstech.tripplanner.booking.entity.Notification;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.BookingCreateRequestModal;
import com.amstech.tripplanner.booking.modal.request.BookingUpdateRequestModal;
import com.amstech.tripplanner.booking.modal.response.BookingReaponseModal;
import com.amstech.tripplanner.booking.repo.BookingRepo;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Service
public class BookingService {

	private final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TripRepo tripRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private BookingEntityToModalConverter bookingEntityToModalConverter;
	@Autowired
	private BookingModalToEntityConverter bookingModalToEntityConverter;

	private int requestSentId = 11;

	public BookingService() {
		LOGGER.debug("BookingService : Object Created");
	}

	public BookingReaponseModal create(BookingCreateRequestModal bookingCreateRequestModal) throws Exception {
		List<Booking> bookingExits = bookingRepo.findByUserIdTripId(bookingCreateRequestModal.getUserId(),
				bookingCreateRequestModal.getTripId());
		if (bookingExits != null) {
			for (Booking bookingExit : bookingExits) {
				if (bookingExit.getStatus().getId() == requestSentId)
					throw new Exception("Booking Is Already Exist and which is Request Sent Status with id : "
							+ bookingExit.getId());
			}

		}
		Booking booking = bookingModalToEntityConverter.create(bookingCreateRequestModal);
		Booking savedBooking = bookingRepo.save(booking);
		return bookingEntityToModalConverter.findById(savedBooking);
	}

	public BookingReaponseModal updateStatus(BookingUpdateRequestModal bookingUpdateRequestModal) throws Exception {
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
		Booking booking = bookingModalToEntityConverter.updatStatus(bookingUpdateRequestModal);
		Booking updatedBooking = bookingRepo.save(booking);
		return bookingEntityToModalConverter.findById(updatedBooking);
	}

	public BookingReaponseModal findById(Integer id) throws Exception {
		Optional<Booking> bookingOptional = bookingRepo.findById(id);
		if (!bookingOptional.isPresent()) {
			throw new Exception("Booking Is no Available with id  : " + id);
		}
		Booking booking = bookingOptional.get();
		BookingReaponseModal bookingReaponseModal = bookingEntityToModalConverter.findById(booking);
		return bookingReaponseModal;
	}
	

	public List<BookingReaponseModal> findByUserId(Integer userId,Integer page,Integer size) throws Exception {
		Optional<User> userOptional = userRepo.findById(userId);
		if (!userOptional.isPresent()) {
			throw new Exception("Seneder Is no Available with id  : " + userId);
		}
		List<Booking> bookings = bookingRepo.findByUserId(userId,PageRequest.of(page, size));
		if (bookings.isEmpty()) {
			throw new Exception("No bookings Available with userId : " + userId);
		}
		List<BookingReaponseModal> bookingReaponseModals = bookingEntityToModalConverter.findByUserId(bookings);
		return bookingReaponseModals;
	}
	public long countByUserId(Integer userId) {
		return bookingRepo.countByUserId(userId);
	}
	
	public List<BookingReaponseModal> findAll(Integer page,Integer size) throws Exception {
		List<Booking> bookings = bookingRepo.findAllBooking(PageRequest.of(page, size));
		if (bookings.isEmpty()) {
			throw new Exception("No bookings Available");
		}
		List<BookingReaponseModal> bookingReaponseModals = bookingEntityToModalConverter.findByUserId(bookings);
		return bookingReaponseModals;
	}
	public long countAllBooking() {
		return bookingRepo.countAllBooking();
	}
}
