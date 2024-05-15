package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "document")
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime createdTime;

    private String image;

    private Integer numView;

    private String description;

    private String linkFile;

    private Boolean actived = false;

    private String nameSubject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private Subject subject;

    @OneToMany(mappedBy = "document", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<DocumentUser> documentUsers = new HashSet<>();

//    @OneToMany(mappedBy = "document", cascade = CascadeType.REMOVE)
//    @JsonManagedReference
//    private Set<DocumentCategory> documentCategories = new HashSet<>();

//    @OneToMany(mappedBy = "document", cascade = CascadeType.REMOVE)
//    @JsonManagedReference
//    private Set<DocumentFile> documentFiles = new HashSet<>();
}
