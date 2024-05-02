package com.web.repository;

import com.web.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select sbj from Subject sbj")
    Page<Subject> getAllSubject(Pageable pageable);

    @Query("select sbj from Subject sbj where sbj.nameSubject like ?1")
    Page<Subject> findSubjectsByName(String nameSubject, Pageable pageable);

    @Query("select sbj from Subject sbj where sbj.specialize.id = ?1")
    Page<Subject> getSubjectsBySpecialize(Long specializeId, Pageable pageable);

    @Query("select sbj from Subject sbj " +
            "join Specialize s on sbj.specialize.id = s.id " +
            "join Department d on s.department.id = d.id " +
            "where d.id = ?1")
    Page<Subject> getSubjectsByDepartment(Long departmentId, Pageable pageable);

    @Query("select sbj from Subject sbj " +
            "join Specialize s on sbj.specialize.id = s.id " +
            "join Department d on s.department.id = d.id " +
            "where d.id = ?1 and s.id = ?2")
    Page<Subject> getSubjectsByDepartmentAndSpecialize(Long departmentId, Long specializeId, Pageable pageable);
}
