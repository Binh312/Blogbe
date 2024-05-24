package com.web.repository;

import com.web.entity.Specialize;
import com.web.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select sbj from Subject sbj")
    Page<Subject> getAllSubject(Pageable pageable);

    @Query("select sbj from Subject sbj where sbj.nameSubject like %?1% or sbj.codeSubject like %?1%")
    Page<Subject> findSubjectsByName(String keywords, Pageable pageable);

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

    @Query("select sbj from Subject sbj " +
            "join Specialize s on sbj.specialize.id = s.id " +
            "join Department d on s.department.id = d.id " +
            "where d.id = ?1")
    List<Subject> findSubjectsByDepartment(Long departmentId);

    @Query("select sbj from Subject sbj where sbj.specialize.id = ?1")
    List<Subject> findSubjectsBySpecialize(Long specializeId);

    @Query("select sbj from Subject sbj where sbj.codeSubject like ?1")
    Optional<Subject> findSubjectByCode(String code);

    @Query("select sbj from Subject sbj where sbj.nameSubject like ?1")
    Optional<Subject> findSubjectByName(String name);

    @Query("select sbj from Subject sbj where sbj.id != ?1")
    List<Subject> findSubjectByOtherId(Long id);
}
