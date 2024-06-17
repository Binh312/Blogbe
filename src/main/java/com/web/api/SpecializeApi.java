package com.web.api;

import com.web.dto.request.SpecializeRequest;
import com.web.dto.response.DepartmentResponse;
import com.web.dto.response.SpecializeResponse;
import com.web.entity.Specialize;
import com.web.repository.SpecializeRepository;
import com.web.service.SpecializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/document-manager/save-update")
    public ResponseEntity<?> saveAndUpdateSpecialize(@RequestBody SpecializeRequest specializeRequest){
        Specialize specialize = specializeService.saveAndUpdateSpecialize(specializeRequest);
        SpecializeResponse specializeResponse = SpecializeResponse.converterSpecializeToSpecializeResponse(specialize);
        return new ResponseEntity<>(specializeResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/document-manager/delete")
    public ResponseEntity<?> deleteSpecialize(@RequestParam Long specializeId){
        String mess = specializeService.deleteSpecialize(specializeId);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    @GetMapping("/public/get-specialize-by-department")
    public ResponseEntity<?> getSpecializeByDepartment(@RequestParam Long departmentId){
        List<Specialize> specializes = specializeService.getSpecializeByDepartment(departmentId);
        List<SpecializeResponse> specializeResponses = specializes.stream().map(
                SpecializeResponse::converterSpecializeToSpecializeResponse
        ).collect(Collectors.toList());
        return new ResponseEntity<>(specializeResponses, HttpStatus.OK);
    }

    @GetMapping("/document-manager/get-all")
    public ResponseEntity<?> adminGetAllSpecialize(@RequestParam String keywords, Pageable pageable){
        Page<Specialize> page = specializeService.adminGetAllSpecialize(keywords, pageable);
        Page<SpecializeResponse> specializeResponses = page.map(SpecializeResponse::converterSpecializeToSpecializeResponse);
        return new ResponseEntity<>(specializeResponses, HttpStatus.OK);
    }

    @GetMapping("/public/find-by-id")
    public ResponseEntity<?> findSpecializeById(@RequestParam Long specializeId){
        Specialize specialize = specializeService.findSpecializeById(specializeId);
        SpecializeResponse specializeResponse = SpecializeResponse.converterSpecializeToSpecializeResponse(specialize);
        return new ResponseEntity<>(specializeResponse, HttpStatus.OK);
    }
}
