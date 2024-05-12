package com.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "blog")
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;

    private String description;

    private String image;

    private String content;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime createdTime;

    private Integer numLike;

    private Integer numComment;

    private Boolean actived = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<BlogCategory> blogCategories;

//    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//    private Set<BlogFile> blogFiles;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<BlogLike> blogLikes;
}
