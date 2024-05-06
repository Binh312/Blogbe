package com.web.api;

import com.web.dto.request.NotificationRequest;
import com.web.entity.Notification;
import com.web.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin
public class NotificationApi {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/admin/add-and-update-notification")
    public ResponseEntity<?> saveAndUpdateNotification(@RequestBody NotificationRequest notificationRequest){
        Notification notification = notificationService.saveAndUpdateNotification(notificationRequest);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-notification")
    public ResponseEntity<?> delete(@RequestParam Long notificationId){
        String mess = notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    @GetMapping("/all/get-all-notification")
    public ResponseEntity<?> getAllNotification(Pageable pageable){
        Page<Notification> page = notificationService.getAllNotification(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/all/get-notification-by-id")
    public ResponseEntity<?> getNotificationById(@RequestParam Long id){
        Notification notification = notificationService.getNotificationById(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
}
