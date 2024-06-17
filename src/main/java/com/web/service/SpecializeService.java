package com.web.service;

import com.web.dto.request.SpecializeRequest;
import com.web.entity.Department;
import com.web.entity.Document;
import com.web.entity.Specialize;
import com.web.entity.Subject;
import com.web.exception.MessageException;
import com.web.mapper.SpecializeMapper;
import com.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializeService {

    @Autowired
    private SpecializeRepository specializeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentUserRepository documentUserRepository;

    @Autowired
    private SpecializeMapper specializeMapper;

    public Specialize saveAndUpdateSpecialize(SpecializeRequest specializeRequest){

        Optional<Department> departmentOptional = departmentRepository.findById(specializeRequest.getDepartmentId());
        Optional<Specialize> specializeCode = specializeRepository.findSpecializeByCode(specializeRequest.getCodeSpecialize());
        Optional<Specialize> specializeName = specializeRepository.findSpecializeByName(specializeRequest.getNameSpecialize());
        Specialize specialize = specializeMapper.converterRequestToSpecialize(specializeRequest);

        if (specializeRequest.getId() == null) {
            if (specializeCode.isPresent() && specializeName.isPresent()){
                throw new MessageException("Mã Chuyên Ngành và tên Chuyên Ngành đã tồn tại");
            }
            if (specializeCode.isPresent()){
                throw new MessageException("Mã Chuyên Ngành đã tồn tại!");
            }
            if (specializeName.isPresent()){
                throw new MessageException("Tên Chuyên Ngành đã tồn tại!");
            }
            if (departmentOptional.isEmpty()) {
                throw new MessageException("Id Khoa không tồn tại");
            }
            specialize.setIdDepartment(departmentOptional.get().getId());
            specialize.setDepartment(departmentOptional.get());
            return specializeRepository.save(specialize);
        }

        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeRequest.getId());
        if (specializeOptional.isEmpty()){
            throw new MessageException("Chuyên ngành này không tồn tại");
        }
        if (departmentOptional.isEmpty()) {
            throw new MessageException("Id Khoa không tồn tại");
        }
        specializeOptional.get().setIdDepartment(departmentOptional.get().getId());
        specializeOptional.get().setDepartment(departmentOptional.get());

        List<Specialize> specializes = specializeRepository.findSpecializeByOtherId(specializeRequest.getId());
        for (Specialize item: specializes) {
            //Kiểm tra mã chuyên ngành đã tồn tại chưa
            if (specializeRequest.getCodeSpecialize().equals(item.getCodeSpecialize())) {
                throw new MessageException("Mã Chuyên ngành đã tồn tại");
            }
            if (specializeRequest.getCodeSpecialize().isEmpty()) {
                specializeOptional.get().setCodeSpecialize(specializeOptional.get().getCodeSpecialize());
            }
            else {
                specializeOptional.get().setCodeSpecialize(specializeRequest.getCodeSpecialize());
            }
            //Kiểm tra tên chuyên ngành đã tồn tại chưa
            if (specializeRequest.getNameSpecialize().equals(item.getNameSpecialize())) {
                throw new MessageException("Tên Chuyên ngành đã tồn tại");
            }
            if (specializeRequest.getNameSpecialize().isEmpty()) {
                specializeOptional.get().setNameSpecialize(specializeOptional.get().getNameSpecialize());
            }
            else {
                specializeOptional.get().setNameSpecialize(specializeRequest.getNameSpecialize());
            }
        }

        return specializeRepository.save(specializeOptional.get());
    }

    @Transactional
    public String deleteSpecialize(Long specializeId){
        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeId);
        if (specializeOptional.isEmpty()){
            throw new MessageException("Ngành học không tồn tại!");
        }

        List<Document> documents = documentRepository.findDocumentBySpecialize(specializeId);
        for (Document document: documents) {
            documentUserRepository.deleteAll(documentUserRepository.findAllByDocumentId(document.getId()));
        }
        documentRepository.deleteAll(documents);

        List<Subject> subjects = subjectRepository.findSubjectsBySpecialize(specializeId);
        subjectRepository.deleteAll(subjects);

        specializeRepository.delete(specializeOptional.get());

        return "Đã xoá Ngành học thành công";
    }

    public List<Specialize> getSpecializeByDepartment(Long departmentId){
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()){
            return specializeRepository.getAllSpecialize();
        } else {
            return specializeRepository.findSpecializesByDepartment(departmentId);
        }
    }

    public Page<Specialize> adminGetAllSpecialize(String keywords, Pageable pageable){
        if (keywords.isEmpty()){
            return specializeRepository.adminGetAllSpecialize(pageable);
        } else {
            return specializeRepository.searchSpecialize(keywords, pageable);
        }
    }

    public Specialize findSpecializeById(Long specializeId){
        Optional<Specialize> specializeOptional = specializeRepository.findById(specializeId);
        if (specializeOptional.isEmpty()){
            throw new MessageException("Ngành học không tồn tại");
        }
        return specializeOptional.get();
    }
}
