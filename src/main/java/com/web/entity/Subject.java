package com.web.entity;

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

    @ManyToOne
    @JoinColumn(name = "specialize_id")
    private Specialize specialize;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Document> documents;
}
