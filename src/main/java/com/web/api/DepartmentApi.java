package com.web.api;

import com.web.dto.response.DepartmentResponse;
import com.web.entity.Department;
import com.web.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
@CrossOrigin
public class DepartmentApi {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/public/get-all-department")
    public ResponseEntity<?> getAllDepartment(){
        List<Department> departments = departmentService.findAllDepartment();
        List<DepartmentResponse> departmentResponses = departments.stream().map(
                department -> DepartmentResponse.converterDepaertmentToDepartmentResponse(department)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(departmentResponses, HttpStatus.OK);
    }
}
