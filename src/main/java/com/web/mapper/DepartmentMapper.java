package com.web.mapper;

import com.web.dto.request.DepartmentRequest;
import com.web.entity.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {

    public Department converterRequestToDepartment(DepartmentRequest departmentRequest){
        Department department = new Department();
        department.setId(departmentRequest.getId());
        department.setCodeDepartment(departmentRequest.getCodeDepartment());
        department.setNameDepartment(departmentRequest.getNameDepartment());
        return department;
    }
}
