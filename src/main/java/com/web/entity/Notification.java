package com.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "notification")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;

    private String image;

    private String content;

    private Date createdDate;

    private Time createdTime;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<NotificationFile> notificationFiles;
}
