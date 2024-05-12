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

    @GetMapping("/public/get-all-subject")
    public ResponseEntity<?> getAllAndFindSubjectsByName(@RequestParam(required = false) String keywords,
                                                         @RequestParam(required = false) Long departmentId,
                                                         @RequestParam(required = false) Long specializeId,
                                                         Pageable pageable){
        Page<Subject> page = subjectService.getAllSubjects(keywords,departmentId,specializeId,pageable);
        Page<SubjectResponse> subjectResponses = page.map(SubjectResponse::converterSubjectToSubjectResponse);
        return new ResponseEntity<>(subjectResponses, HttpStatus.OK);
    }
}
