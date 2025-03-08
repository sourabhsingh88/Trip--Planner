package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;

import lombok.Data;


@Data
public class NotificationResponseModal {

	private int id;
	private int senderId;
	private int receiverId;
	private String senderName;
	private String receiverName;
	private int tripId;
	private String tripName;
	private String StatusName;
	private String title;
	private String message;
	private Date createdAt;
	
	
}
