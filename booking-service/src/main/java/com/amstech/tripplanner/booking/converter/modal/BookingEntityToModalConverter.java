package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Booking;
import com.amstech.tripplanner.booking.modal.response.BookingReaponseModal;

@Component
public class BookingEntityToModalConverter {

	public BookingReaponseModal findById(Booking booking) {
		BookingReaponseModal bookingReaponseModal = new BookingReaponseModal();
		bookingReaponseModal.setId(booking.getId());
		bookingReaponseModal.setTripName(booking.getTrip().getName());
		bookingReaponseModal.setTripPlannerName(booking.getTrip().getTripPlanner().getUser().getName());
		bookingReaponseModal.setTripPrice(booking.getTrip().getPrice());
		bookingReaponseModal.setTripDescription(booking.getTrip().getDescription());
		bookingReaponseModal.setStatusName(booking.getStatus().getName());
		return bookingReaponseModal;
	}
	
	public List<BookingReaponseModal> findByUserId(List<Booking> bookings) {

		List<BookingReaponseModal> bookingReaponseModals = new ArrayList<>();
		for (Booking booking : bookings) {
			BookingReaponseModal bookingReaponseModal = new BookingReaponseModal();
			bookingReaponseModal.setId(booking.getId());
			bookingReaponseModal.setTripName(booking.getTrip().getName());
			bookingReaponseModal.setTripPlannerName(booking.getTrip().getTripPlanner().getUser().getName());
			bookingReaponseModal.setTripPrice(booking.getTrip().getPrice());
			bookingReaponseModal.setTripDescription(booking.getTrip().getDescription());
			bookingReaponseModal.setStatusName(booking.getStatus().getName());

			bookingReaponseModals.add(bookingReaponseModal);

		}
		return bookingReaponseModals;
	}
}
