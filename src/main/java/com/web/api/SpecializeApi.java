package com.web.api;

import com.web.entity.Specialize;
import com.web.service.SpecializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialize")
@CrossOrigin
public class SpecializeApi {

    @Autowired
    private SpecializeService specializeService;

    @GetMapping("/public/get-specialize-by-department")
    public ResponseEntity<?> getSpecializeByDepartment(@RequestParam Long departmentId){
        List<Specialize> specializes = specializeService.getSpecializeByDepartment(departmentId);
        return new ResponseEntity<>(specializes, HttpStatus.OK);
    }

}
