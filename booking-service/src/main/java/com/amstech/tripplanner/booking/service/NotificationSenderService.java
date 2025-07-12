package com.amstech.tripplanner.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amstech.tripplanner.booking.modal.request.NotificationCreateRequestModal;

@Service
public class NotificationSenderService {

	 @Autowired
	    private RestTemplate restTemplate;

	    private static final String NOTIFY_API_URL = "http://localhost:1002/booking-service-api-local/notification/create";

	    public void send(NotificationCreateRequestModal notificationCreateRequestModal) {
	        try {
	            restTemplate.postForObject(NOTIFY_API_URL, notificationCreateRequestModal, String.class);
	            System.out.println(" Notification sent successfully!");
	        } catch (Exception e) {
	            System.err.println(" Notification failed: " + e.getMessage());
	        }
	    }
}
