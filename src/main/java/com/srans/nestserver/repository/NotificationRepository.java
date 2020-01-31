package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query(value = "	select notificationId from NotificationUser where viewStatus='N'")
	public Long[] getNotificationId();

	//get all new notification
	@Query(value = "SELECT n.id, n.message, n.tenantId from Notification n  INNER JOIN NotificationUser nu on (n.id=nu.notificationId) where nu.viewStatus='N' and nu.userId=?1")
	public List<Object[]> getAllNotification(Long userId);
	
	
	/*
	 * @Query(value = "select n.message  from Notification n  INNER \r\n" +
	 * "JOIN NotificationUser nu\r\n" +
	 * "on(n.id=nu.notificationId) where nu.viewStatus='N' and nu.userId=?1",
	 * nativeQuery = true) public List<Object> getAllUserNotifications(Long userId);
	 */
	 

}
