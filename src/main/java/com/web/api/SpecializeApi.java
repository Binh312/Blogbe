package com.web.api;

import com.web.dto.response.DepartmentResponse;
import com.web.dto.response.SpecializeResponse;
import com.web.entity.Specialize;
import com.web.service.SpecializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/specialize")
@CrossOrigin
public class SpecializeApi {

    @Autowired
    private SpecializeService specializeService;

    @GetMapping("/public/get-specialize-by-department")
    public ResponseEntity<?> getSpecializeByDepartment(@RequestParam Long departmentId){
        List<Specialize> specializes = specializeService.getSpecializeByDepartment(departmentId);
        List<SpecializeResponse> specializeResponses = specializes.stream().map(
                specialize -> SpecializeResponse.converterSpecializeToSpecializeResponse(specialize)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(specializeResponses, HttpStatus.OK);
    }
}
