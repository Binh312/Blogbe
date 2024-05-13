package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "document_user")
@Getter
@Setter
public class DocumentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonBackReference
    private Document document;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
