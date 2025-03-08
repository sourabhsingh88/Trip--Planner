package com.amstech.tripplanner.booking.modal.request;

import lombok.Data;

@Data
public class NotificationCreateRequestModal {
	private int senderId;
	private int receiverId;
	private int tripId;
	private String title;
	private String message;
	
}
