package com.web.service;

import com.web.entity.Department;
import com.web.entity.Specialize;
import com.web.entity.Subject;
import com.web.exception.MessageException;
import com.web.repository.DepartmentRepository;
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

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Subject> getSubjectBySpecialize(Long specializeId){
        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeId);
        if (specializeOptional.isEmpty()){
            return null;
        } else {
            return subjectRepository.getSubjectsBySpecialize(specializeId);
        }
    }

    public List<Subject> getSubjectByDepartmentAndSpecialize(Long departmentId, Long specializeId){
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()){
            throw new MessageException("Khoa không tồn tại");
        }
        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeId);
        if (specializeOptional.isEmpty()){
            throw new MessageException("Chuyên ngành không tồn tại");
        }

        return subjectRepository.getSubjectsByDepartmentAndSpecialize(departmentId,specializeId);
    }
}
