package com.web.service;

import com.web.entity.Department;
import com.web.entity.Specialize;
import com.web.entity.Subject;
import com.web.exception.MessageException;
import com.web.repository.DepartmentRepository;
import com.web.repository.SpecializeRepository;
import com.web.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SpecializeRepository specializeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<Subject> getAllSubject(Pageable pageable){
        return subjectRepository.getAllSubject(pageable);
    }

    public Page<Subject> findSubjectsByName(String nameSubject, Pageable pageable){
        return subjectRepository.findSubjectsByName(nameSubject,pageable);
    }

    public Page<Subject> getSubjectByDepartmentAndSpecialize(Long departmentId, Long specializeId, Pageable pageable){
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeId);
        if (departmentOptional.isEmpty() && specializeOptional.isEmpty()){
            return subjectRepository.getAllSubject(pageable);
        }
        else if (departmentOptional.isPresent() && specializeOptional.isEmpty()) {
            return subjectRepository.getSubjectsByDepartment(departmentId,pageable);
        }
        else {
            return subjectRepository.getSubjectsByDepartmentAndSpecialize(departmentId,specializeId,pageable);
        }
    }
}
