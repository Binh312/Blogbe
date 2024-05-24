package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String codeSubject;

    private String nameSubject;

    private Long connecIdSpecialize;

    @ManyToOne
    @JoinColumn(name = "specialize_id")
    @JsonBackReference
    private Specialize specialize;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Document> documents;
}
