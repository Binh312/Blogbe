package com.web.service;

import com.web.entity.Department;
import com.web.entity.Specialize;
import com.web.repository.DepartmentRepository;
import com.web.repository.SpecializeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializeService {

    @Autowired
    private SpecializeRepository specializeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Specialize> getSpecializeByDepartment(Long departmentId){
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()){
            return null;
        } else {
            return specializeRepository.getSpecializesByDepartment(departmentId);
        }
    }
}
