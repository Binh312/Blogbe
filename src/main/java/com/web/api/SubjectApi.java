package com.web.api;

import com.web.dto.response.SpecializeResponse;
import com.web.dto.response.SubjectResponse;
import com.web.entity.Subject;
import com.web.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin
public class SubjectApi {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/public/get-all-subject")
    public ResponseEntity<?> getAllSubject(Pageable pageable){
        Page<Subject> page = subjectService.getAllSubject(pageable);
        Page<SubjectResponse> subjectResponses = page.map(SubjectResponse::converterSubjectToSubjectResponse);
        return new ResponseEntity<>(subjectResponses, HttpStatus.OK);
    }

    @GetMapping("/public/find-subject-by-name")
    public ResponseEntity<?> findSubjectsByName(@RequestBody String nameSubject, Pageable pageable){
        Page<Subject> page = subjectService.findSubjectsByName(nameSubject,pageable);
        Page<SubjectResponse> subjectResponses = page.map(SubjectResponse::converterSubjectToSubjectResponse);
        return new ResponseEntity<>(subjectResponses, HttpStatus.OK);
    }

    @GetMapping("/public/get-subject-by-department-and-specialize")
    public ResponseEntity<?> getSubjectByDepartmentAndSpecialize(@RequestParam  Long departmentId, Long specializeId, Pageable pageable){
        Page<Subject> subjects = subjectService.getSubjectByDepartmentAndSpecialize(departmentId,specializeId,pageable);
        Page<SubjectResponse> subjectResponses = subjects.map(SubjectResponse::converterSubjectToSubjectResponse);
        return new ResponseEntity<>(subjectResponses, HttpStatus.OK);
    }
}
