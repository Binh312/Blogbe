package com.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String codeDepartment;

    private String nameDepartment;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Specialize> specializes;
}
