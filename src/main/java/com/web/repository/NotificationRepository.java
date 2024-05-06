package com.web.repository;

import com.web.entity.Blog;
import com.web.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n order by n.createdDate desc, n.createdTime desc")
    Page<Notification> getAllNotification(Pageable pageable);

    @Query("select n from Notification n where n.id = ?1")
    Optional<Notification> getNotificationById(Long id);
}
