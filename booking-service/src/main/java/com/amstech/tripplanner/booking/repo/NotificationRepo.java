package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.tripplanner.booking.entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

	@Query("select n from Notification n where n.sender.id =:senderId and n.receiver.id =:receiverId")
	List<Notification> findBySenderIdReciver(@Param("senderId") int senderId, @Param("receiverId") int receiverId);
	
	@Query("select n from Notification n where n.receiver.id =:receiverId and n.status.id =:unRead")
	List<Notification> findAllByReceiverId( @Param("receiverId") int receiverId,@Param("unRead") int unRead,Pageable pageable);
	
	@Query("select count(n) from Notification n where n.receiver.id =:receiverId and n.status.id =:unRead")
	long countAllByReceiverId( @Param("receiverId") int receiverId,@Param("unRead") int unRead);
	

}
