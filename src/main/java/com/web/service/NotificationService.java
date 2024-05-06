package com.web.service;

import com.web.dto.request.FileDto;
import com.web.dto.request.NotificationRequest;
import com.web.entity.BlogFile;
import com.web.entity.Notification;
import com.web.entity.NotificationFile;
import com.web.exception.MessageException;
import com.web.mapper.NotificationMapper;
import com.web.repository.NotificationFileRepository;
import com.web.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationFileRepository notificationFileRepository;

    public Notification addNewNotification(NotificationRequest request){
        Notification notification = notificationMapper.convertRequestToNotification(request);
        notification.setCreatedDate(new Date(System.currentTimeMillis()));
        notification.setCreatedTime(new Time(System.currentTimeMillis()));

        Notification result = notificationRepository.save(notification);

        List<NotificationFile> notificationFiles = new ArrayList<>();
        for (FileDto FileDto : request.getLinkFiles()) {
            NotificationFile notificationFile = new NotificationFile();
            notificationFile.setNotification(result);
            notificationFile.setLinkFile(FileDto.getLinkFile());
            notificationFile.setFileName(FileDto.getFileName());
            notificationFile.setFileSize(FileDto.getFileSize());
            notificationFile.setFileType(FileDto.getTypeFile());
            notificationFiles.add(notificationFile);
        }
        notificationFileRepository.saveAll(notificationFiles);

        return result;
    }

    public Notification updateNotification(NotificationRequest request){
        Optional<Notification> notificationOptional = notificationRepository.findById(request.getId());
        if (notificationOptional.isEmpty()){
            throw new MessageException("Thông báo không tồn tại");
        }

        Notification notification = notificationMapper.convertRequestToNotification(request);
        notification.setCreatedDate(new Date(System.currentTimeMillis()));
        notification.setCreatedTime(new Time(System.currentTimeMillis()));

        Notification result = notificationRepository.save(notification);

        notificationFileRepository.deleteByNotification(request.getId());
        List<NotificationFile> notificationFiles = new ArrayList<>();
        for (FileDto FileDto : request.getLinkFiles()) {
            NotificationFile notificationFile = new NotificationFile();
            notificationFile.setNotification(result);
            notificationFile.setLinkFile(FileDto.getLinkFile());
            notificationFile.setFileName(FileDto.getFileName());
            notificationFile.setFileSize(FileDto.getFileSize());
            notificationFile.setFileType(FileDto.getTypeFile());
            notificationFiles.add(notificationFile);
        }
        notificationFileRepository.saveAll(notificationFiles);

        return result;
    }

    public String deleteNotification(Long notificationId){
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
        if (notificationOptional.isEmpty()){
            throw new MessageException("Thông báo không tồn tại");
        }

        notificationRepository.delete(notificationOptional.get());
        return "Đã xoá thông báo thành công";
    }

    public Page<Notification> getAllNotification(Pageable pageable){
        return notificationRepository.getAllNotification(pageable);
    }

    public Notification getNotificationById(Long id){
        Optional<Notification> notificationOptional = notificationRepository.getNotificationById(id);
        if (notificationOptional.isEmpty()) {
            throw new MessageException("Thông báo không tồn tại");
        }
        return notificationOptional.get();
    }
}
