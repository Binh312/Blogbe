package com.web.repository;

import com.web.entity.Specialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializeRepository extends JpaRepository<Specialize, Long> {

    @Query("select s from Specialize s where s.department.id = ?1")
    List<Specialize> getSpecializesByDepartment(Long departmentId);
}
