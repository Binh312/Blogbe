package com.web.service;

import com.web.entity.Specialize;
import com.web.entity.Subject;
import com.web.repository.SpecializeRepository;
import com.web.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SpecializeRepository specializeRepository;

    public List<Subject> getSubjectBySpecialize(Long specializeId){
        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeId);
        if (specializeOptional.isEmpty()){
            return null;
        } else {
            return subjectRepository.getSubjectsBySpecialize(specializeId);
        }
    }
}
