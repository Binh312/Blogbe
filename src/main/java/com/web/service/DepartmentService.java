package com.web.service;

import com.web.dto.response.DepartmentResponse;
import com.web.entity.Department;
import com.web.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllDepartment(){
        return departmentRepository.getAllDepartment();
    }

}
