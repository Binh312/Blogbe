package com.web.api;

import com.web.dto.response.SubjectResponse;
import com.web.entity.Subject;
import com.web.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin
public class SubjectApi {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/public/get-all-and-find-subject-by-name")
    public ResponseEntity<?> getAllAndFindSubjectsByName(@RequestParam(required = false) String keywords, Pageable pageable){
        Page<Subject> page = subjectService.getAllAndFindSubjectsByName(keywords,pageable);
        Page<SubjectResponse> subjectResponses = page.map(SubjectResponse::converterSubjectToSubjectResponse);
        return new ResponseEntity<>(subjectResponses, HttpStatus.OK);
    }

    @GetMapping("/public/get-subject-by-department-and-specialize")
    public ResponseEntity<?> getSubjectByDepartmentAndSpecialize(@RequestParam(required = false) Long departmentId,
                                                                 @RequestParam(required = false) Long specializeId,
                                                                 Pageable pageable){
        Page<Subject> subjects = subjectService.getSubjectByDepartmentAndSpecialize(departmentId,specializeId,pageable);
        Page<SubjectResponse> subjectResponses = subjects.map(SubjectResponse::converterSubjectToSubjectResponse);
        return new ResponseEntity<>(subjectResponses, HttpStatus.OK);
    }
}
