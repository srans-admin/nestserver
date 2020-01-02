package com.srans.nestserver.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query(value = "	select id from notification where view_status='N'", nativeQuery = true)
	public Long[] getNotificationId();

	@Query(value = "select n.message, n.notification_category, n.user_role, n.view_status, nu.notification_id from notification n  inner\r\n"
			+ "JOIN notificationuser nu\r\n"
			+ "on(n.id=nu.notification_id) where nu.super_admin_code=?1", nativeQuery = true)
	public Set<Object> findNewNotification(Long superAdminUserCode);

}
