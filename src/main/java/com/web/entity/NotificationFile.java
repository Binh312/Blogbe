package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notification_file")
@Getter
@Setter
public class NotificationFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String linkFile;

    private String fileName;

    private Long fileSize;

    private String fileType;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    @JsonBackReference
    private Notification notification;
}
