package com.web.service;

import com.web.dto.request.DepartmentRequest;
import com.web.dto.response.DepartmentResponse;
import com.web.entity.Department;
import com.web.entity.Document;
import com.web.entity.Specialize;
import com.web.entity.Subject;
import com.web.exception.MessageException;
import com.web.mapper.DepartmentMapper;
import com.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SpecializeRepository specializeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentUserRepository documentUserRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    public Department saveAndUpdate(DepartmentRequest departmentRequest){

        Optional<Department> departmentName = departmentRepository.findDepartmentByName(departmentRequest.getNameDepartment());
        Optional<Department> departmentCode = departmentRepository.findDepartmentByCode(departmentRequest.getCodeDepartment());
        Department department = departmentMapper.converterRequestToDepartment(departmentRequest);

        if (departmentRequest.getId() == null) {
            if (departmentCode.isPresent() && departmentName.isPresent()){
                throw new MessageException("Mã Khoa và tên Khoa đã tồn tại");
            }
            if (departmentCode.isPresent()){
                throw new MessageException("Mã Khoa đã tồn tại!");
            }
            if (departmentName.isPresent()){
                throw new MessageException("Tên Khoa đã tồn tại!");
            }
            return departmentRepository.save(department);
        }

        Optional<Department> departmentOptional = departmentRepository.findById(departmentRequest.getId());
        if (departmentOptional.isEmpty()){
            throw new MessageException("Khoa này không tồn tại");
        }

        List<Department> departments = departmentRepository.getDepartmentById(departmentRequest.getId());
        for (Department item: departments) {
            //Kiểm tra mã Khoa đã tồn tại chưa
            if (departmentRequest.getCodeDepartment().equals(item.getCodeDepartment())) {
                throw new MessageException("Mã Khoa đã tồn tại");
            }
            if (departmentRequest.getCodeDepartment().isEmpty()) {
                departmentOptional.get().setCodeDepartment(departmentOptional.get().getCodeDepartment());
            }
            else {
                departmentOptional.get().setCodeDepartment(departmentRequest.getCodeDepartment());
            }
            //Kiểm tra tên khoa đã tồn tại chưa
            if (departmentRequest.getNameDepartment().equals(item.getNameDepartment())) {
                throw new MessageException("Tên Khoa đã tồn tại");
            }
            if (departmentRequest.getNameDepartment().isEmpty()) {
                departmentOptional.get().setNameDepartment(departmentOptional.get().getNameDepartment());
            }
            else {
                departmentOptional.get().setNameDepartment(departmentRequest.getNameDepartment());
            }
        }

        return departmentRepository.save(departmentOptional.get());
    }

    @Transactional
    public String deleteDepartment(Long departmentId){
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()){
            throw new MessageException("Khoa này không tồn tại");
        }

        List<Document> documents = documentRepository.findDocumentByDepartment(departmentId);
        for (Document document: documents) {
            documentUserRepository.deleteAll(documentUserRepository.findAllByDocumentId(document.getId()));
        }
        documentRepository.deleteAll(documents);

        List<Subject> subjects = subjectRepository.findSubjectsByDepartment(departmentId);
        subjectRepository.deleteAll(subjects);

        List<Specialize> specializes = specializeRepository.findSpecializesByDepartment(departmentId);
        specializeRepository.deleteAll(specializes);

        departmentRepository.delete(departmentOptional.get());

        return "Đã xoá Khoa thành công";
    }

    public List<Department> findAllDepartment(){
        return departmentRepository.getAllDepartment();
    }

}
