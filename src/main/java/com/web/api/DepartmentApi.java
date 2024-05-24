package com.web.api;

import com.web.dto.request.DepartmentRequest;
import com.web.dto.response.DepartmentResponse;
import com.web.entity.Department;
import com.web.repository.SpecializeRepository;
import com.web.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
@CrossOrigin
public class DepartmentApi {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SpecializeRepository specializeRepository;

    @PostMapping("/document-manager/save-update")
    public ResponseEntity<?> saveOrUpdate(@RequestBody DepartmentRequest departmentRequest){
        Department department = departmentService.saveAndUpdate(departmentRequest);
        DepartmentResponse departmentResponses = DepartmentResponse.converterDepaertmentToDepartmentResponse(department);
        return new ResponseEntity<>(departmentResponses, HttpStatus.OK);
    }

    @DeleteMapping("/document-manager/delete")
    public ResponseEntity<?> deleteDepartment(@RequestParam Long departmentId){
        String mess = departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    @GetMapping("/public/get-all-department")
    public ResponseEntity<?> getAllDepartment(){
        List<Department> departments = departmentService.findAllDepartment();
        List<DepartmentResponse> departmentResponses = departments.stream().map(
                DepartmentResponse::converterDepaertmentToDepartmentResponse
        ).collect(Collectors.toList());
        return new ResponseEntity<>(departmentResponses, HttpStatus.OK);
    }
}
