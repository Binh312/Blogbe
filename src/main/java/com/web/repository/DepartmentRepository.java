package com.web.repository;

import com.web.dto.response.DepartmentResponse;
import com.web.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d order by d.nameDepartment asc")
    List<Department> getAllDepartment();

    @Query("select d from Department d where d.nameDepartment like ?1")
    Optional<Department> findDepartmentByName(String Name);

    @Query("select d from Department d where d.codeDepartment like ?1")
    Optional<Department> findDepartmentByCode(String Code);

    @Query("select d from Department d where d.id != ?1")
    List<Department> getDepartmentById(Long id);
}
