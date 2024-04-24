package com.web.api;

import com.web.entity.Subject;
import com.web.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin
public class SubjectApi {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/public/get-subject-by-specialize")
    public ResponseEntity<?> getSubjectBySpecialize(@RequestParam Long specializeId){
        List<Subject> subjects = subjectService.getSubjectBySpecialize(specializeId);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

}
