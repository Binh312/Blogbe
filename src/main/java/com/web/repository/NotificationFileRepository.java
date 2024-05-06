package com.web.repository;

import com.web.entity.NotificationFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NotificationFileRepository extends JpaRepository<NotificationFile, Long> {

    @Modifying
    @Transactional
    @Query("delete from NotificationFile n where n.notification.id = ?1")
    Long deleteByNotification(Long notificationId);
}
