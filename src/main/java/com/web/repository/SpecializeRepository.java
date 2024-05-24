package com.web.repository;

import com.web.entity.Specialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializeRepository extends JpaRepository<Specialize, Long> {

    @Query("select s from Specialize s where s.department.id = ?1")
    List<Specialize> findSpecializesByDepartment(Long departmentId);

    @Query("select s from Specialize s")
    List<Specialize> getAllSpecialize();

    @Query("select s from Specialize s where s.codeSpecialize like ?1")
    Optional<Specialize> findSpecializeByCode(String code);

    @Query("select s from Specialize s where s.nameSpecialize like ?1")
    Optional<Specialize> findSpecializeByName(String name);

    @Query("select s from Specialize s where s.id != ?1")
    List<Specialize> findSpecializeByOtherId(Long id);
}
