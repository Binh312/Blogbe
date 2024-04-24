package com.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "specialize")
@Getter
@Setter
public class Specialize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String codeSpecialize;

    private String nameSpecialize;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "specialize", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Subject> subjects;
}
