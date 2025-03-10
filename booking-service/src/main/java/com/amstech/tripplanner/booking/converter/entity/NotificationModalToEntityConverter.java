package com.amstech.tripplanner.booking.converter.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Notification;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.NotificationCreateRequestModal;
@Component
public class NotificationModalToEntityConverter {

	public Notification create(NotificationCreateRequestModal notificationCreateRequestModal,Optional<User> senderOptional,Optional<User> receiverOptional,Optional<Trip> tripOptional,Optional<Status> statusOptional) {
		Notification notification = new Notification();
		notification.setSender(senderOptional.get()); 
		notification.setReceiver(receiverOptional.get());
		notification.setTrip(tripOptional.get());
		notification.setTitle(notificationCreateRequestModal.getTitle());
		notification.setMessage(notificationCreateRequestModal.getMessage());
		notification.setStatus(statusOptional.get());
		notification.setCreatedAt(new Timestamp(new Date().getTime()));
		
		return notification;
	}
}
