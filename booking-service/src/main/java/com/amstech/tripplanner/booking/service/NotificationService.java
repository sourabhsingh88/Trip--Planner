package com.amstech.tripplanner.booking.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.converter.entity.NotificationModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.NotificationEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Notification;
import com.amstech.tripplanner.booking.entity.Status;
import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.modal.request.NotificationCreateRequestModal;
import com.amstech.tripplanner.booking.modal.response.NotificationResponseModal;
import com.amstech.tripplanner.booking.repo.NotificationRepo;
import com.amstech.tripplanner.booking.repo.StatusRepo;
import com.amstech.tripplanner.booking.repo.TripRepo;
import com.amstech.tripplanner.booking.repo.UserRepo;

@Service
public class NotificationService {

	private final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TripRepo tripRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private NotificationModalToEntityConverter notificationModalToEntityConverter;
	@Autowired
	private NotificationEntityToModalConverter notificationEntityToModalConverter;

	private int unReadId = 1;
	private int readId = 2;

	public NotificationService() {
		LOGGER.debug("NotificationService : Object Created");
	}

	public NotificationResponseModal create(NotificationCreateRequestModal notificationCreateRequestModal)
			throws Exception {

		Optional<User> senderOptional = userRepo.findById(notificationCreateRequestModal.getSenderId());
		if (!senderOptional.isPresent()) {
			throw new Exception("Seneder Is no Available with id  : " + notificationCreateRequestModal.getSenderId());
		}

		Optional<User> receiverOptional = userRepo.findById(notificationCreateRequestModal.getReceiverId());
		if (!receiverOptional.isPresent()) {
			throw new Exception(
					"Receiver Is no Available with id  : " + notificationCreateRequestModal.getReceiverId());
		}

		Optional<Trip> tripOptional = tripRepo.findById(notificationCreateRequestModal.getTripId());
		if (!tripOptional.isPresent()) {
			throw new Exception("Trip Is no Available with id  : " + notificationCreateRequestModal.getTripId());
		}

		Optional<Status> statusOptional = statusRepo.findById(unReadId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + unReadId);
		}

		List<Notification> notificationExists = notificationRepo.findBySenderIdReciver(senderOptional.get().getId(),
				receiverOptional.get().getId());
		if (notificationExists != null) {
			for (Notification notificationExist : notificationExists) {
				if (notificationExist.getStatus().getId() == unReadId)
					throw new Exception(
							"Notifcation Is Already Send and which is unreaded with id : " + notificationExist.getId());
			}

		}
		Notification notification = notificationModalToEntityConverter.create(notificationCreateRequestModal,
				senderOptional, receiverOptional, tripOptional, statusOptional);
		Notification savedNotification = notificationRepo.save(notification);
		return notificationEntityToModalConverter.findById(savedNotification);

	}

	public void markRead(Integer id) throws Exception {
		Optional<Notification> notificationOptional = notificationRepo.findById(id);
		if (!notificationOptional.isPresent()) {
			throw new Exception("Notification Is not Available with id  : " + id);
		}

		Optional<Status> statusOptional = statusRepo.findById(readId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is not Available with id  : " + readId);
		}

		if (notificationOptional.get().getStatus().getId() == readId) {
			throw new Exception("Notification Is Already readed  with id : " + id);
		}
		Notification notification = notificationOptional.get();
		notification.setStatus(statusOptional.get());
		notificationRepo.save(notification);

	}

	public List<NotificationResponseModal> findAllForReciver(Integer receiverId, Integer page, Integer size)
			throws Exception {

		Optional<User> receiverOptional = userRepo.findById(receiverId);
		if (!receiverOptional.isPresent()) {
			throw new Exception("User Is no Available with id  : " + receiverId);
		}

		List<Notification> notifications = notificationRepo.findAllByReceiverId(receiverId, unReadId,
				PageRequest.of(page, size));
		if (notifications.isEmpty()) {
			throw new Exception("Not Notification Exist for user with id : " + receiverId);
		}

		List<NotificationResponseModal> notificationResponseModals = notificationEntityToModalConverter
				.findAllByReceiverId(notifications);
		return notificationResponseModals;

	}

	public long countAllForReciver(Integer receiverId) throws Exception {

		return notificationRepo.countAllByReceiverId(receiverId, unReadId);
	

	}

}
