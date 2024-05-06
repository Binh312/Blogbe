package com.web.mapper;

import com.web.dto.request.NotificationRequest;
import com.web.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification convertRequestToNotification(NotificationRequest request){
        Notification notification = new Notification();
        notification.setId(request.getId());
        notification.setTitle(request.getTitle());
        notification.setImage(request.getImage());
        notification.setContent(request.getContent());
        return notification;
    }
}
