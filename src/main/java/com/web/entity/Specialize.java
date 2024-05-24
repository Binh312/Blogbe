package com.web.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private Long idDepartment;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    @OneToMany(mappedBy = "specialize", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Subject> subjects;
}
