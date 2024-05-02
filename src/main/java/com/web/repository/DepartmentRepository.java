package com.web.repository;

import com.web.dto.response.DepartmentResponse;
import com.web.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d order by d.nameDepartment asc")
    List<Department> getAllDepartment();
}
