package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Notification;
import com.amstech.tripplanner.booking.modal.response.NotificationResponseModal;

@Component
public class NotificationEntityToModalConverter {
	
	public List<NotificationResponseModal> findAllByReceiverId(List<Notification> notifications){
		List<NotificationResponseModal> notificationResponseModals = new ArrayList<>();
		for (Notification notification : notifications) {
			NotificationResponseModal notificationResponseModal = new NotificationResponseModal();
			notificationResponseModal.setId(notification.getId());
			notificationResponseModal.setReceiverId(notification.getReceiver().getId());
			notificationResponseModal.setReceiverName(notification.getReceiver().getName());
			notificationResponseModal.setSenderId(notification.getSender().getId());
			notificationResponseModal.setSenderName(notification.getSender().getName());
			notificationResponseModal.setTripId(notification.getTrip().getId());
			notificationResponseModal.setTripName(notification.getTrip().getName());
			notificationResponseModal.setTitle(notification.getTitle());
			notificationResponseModal.setMessage(notification.getMessage());
			notificationResponseModal.setStatusName(notification.getStatus().getName());
			notificationResponseModal.setCreatedAt(notification.getCreatedAt());
			
			notificationResponseModals.add(notificationResponseModal);
		}
		return notificationResponseModals;
	}

}
