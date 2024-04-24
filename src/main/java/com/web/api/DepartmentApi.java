package com.web.api;

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

@RestController
@RequestMapping("/api/department")
@CrossOrigin
public class DepartmentApi {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/public/get-all-department")
    public ResponseEntity<?> getAllDepartment(){
        List<Department> departments = departmentService.findAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
