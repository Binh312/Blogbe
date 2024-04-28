package com.web.repository;

import com.web.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select sbj from Subject sbj where sbj.specialize.id = ?1")
    List<Subject> getSubjectsBySpecialize(Long specializeId);

    @Query("select sbj from Subject sbj " +
            "join Specialize s on sbj.specialize.id = s.id " +
            "join Department d on s.department.id = d.id " +
            "where d.id = ?1 and s.id = ?2")
    List<Subject> getSubjectsByDepartmentAndSpecialize(Long departmentId, Long specializeId);
}
