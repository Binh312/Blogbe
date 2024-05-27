package com.web.service;

import com.web.dto.request.SubjectRequest;
import com.web.entity.Document;
import com.web.entity.Specialize;
import com.web.entity.Subject;
import com.web.exception.MessageException;
import com.web.mapper.SubjectMapper;
import com.web.repository.DocumentRepository;
import com.web.repository.DocumentUserRepository;
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
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentUserRepository documentUserRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    public Subject saveAndUpdateSubject(SubjectRequest subjectRequest){
        Optional<Specialize> specializeOptional = specializeRepository.findById(subjectRequest.getSpecializeId());
        Optional<Subject> subjectCode = subjectRepository.findSubjectByCode(subjectRequest.getCodeSubject());
        Optional<Subject> subjectName = subjectRepository.findSubjectByName(subjectRequest.getNameSubject());
        Subject subject = subjectMapper.converterRequestToSubject(subjectRequest);

        if (subjectRequest.getId() == null) {
            if (subjectCode.isPresent() && subjectName.isPresent()){
                throw new MessageException("Mã Môn học và tên Môn học đã tồn tại");
            }
            if (subjectCode.isPresent()){
                throw new MessageException("Mã Môn học đã tồn tại!");
            }
            if (subjectName.isPresent()){
                throw new MessageException("Tên Môn học đã tồn tại!");
            }
            if (specializeOptional.isEmpty()) {
                throw new MessageException("Id Chuyên ngành không tồn tại");
            }
            subject.setConnecIdSpecialize(specializeOptional.get().getId());
            subject.setSpecialize(specializeOptional.get());
            return subjectRepository.save(subject);
        }

        Optional<Subject> subjectOptional = subjectRepository.findById(subjectRequest.getId());
        if (subjectOptional.isEmpty()){
            throw new MessageException("Môn học này không tồn tại");
        }
        if (specializeOptional.isEmpty()) {
            throw new MessageException("Id Chuyên Ngành không tồn tại");
        }
        subjectOptional.get().setConnecIdSpecialize(specializeOptional.get().getId());
        subjectOptional.get().setSpecialize(subjectOptional.get().getSpecialize());

        List<Subject> subjects = subjectRepository.findSubjectByOtherId(subjectRequest.getId());
        for (Subject item: subjects) {
            //Kiểm tra mã môn học đã tồn tại chưa
            if (subjectRequest.getCodeSubject().equals(item.getCodeSubject())) {
                throw new MessageException("Mã Môn học đã tồn tại");
            }
            if (subjectRequest.getCodeSubject().isEmpty()) {
                subjectOptional.get().setCodeSubject(subjectOptional.get().getCodeSubject());
            }
            else {
                subjectOptional.get().setCodeSubject(subjectRequest.getCodeSubject());
            }
            //Kiểm tra tên môn học đã tồn tại chưa
            if (subjectRequest.getNameSubject().equals(item.getNameSubject())) {
                throw new MessageException("Tên Môn học đã tồn tại");
            }
            if (subjectRequest.getNameSubject().isEmpty()) {
                subjectOptional.get().setNameSubject(subjectOptional.get().getNameSubject());
            }
            else {
                subjectOptional.get().setNameSubject(subjectRequest.getNameSubject());
            }
        }

        return subjectRepository.save(subjectOptional.get());
    }

    public String deleteSubject(Long subjectId){
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (subjectOptional.isEmpty()){
            throw new MessageException("Môn học này không tồn tại");
        }

        List<Document> documents = documentRepository.findDocumentBySubject(subjectId);
        for (Document document: documents) {
            documentUserRepository.deleteAll(documentUserRepository.findAllByDocumentId(document.getId()));
        }
        documentRepository.deleteAll(documents);

        subjectRepository.delete(subjectOptional.get());
        return "Đã xoá Môn học thành công";
    }

    public Page<Subject> getAllSubjects(String nameSubject, Long departmentId, Long specializeId, Pageable pageable){

        if (nameSubject.isEmpty() && departmentId == null && specializeId == null){
            return subjectRepository.getAllSubject(pageable);
        } else if (nameSubject.isEmpty() && specializeId == null){
            return subjectRepository.getSubjectsByDepartment(departmentId,pageable);
        } else if (nameSubject.isEmpty()){
            return subjectRepository.getSubjectsByDepartmentAndSpecialize(departmentId,specializeId,pageable);
        } else {
            return subjectRepository.findSubjectsByName(nameSubject,pageable);
        }
    }

    public Subject findSubjectById(Long subjectId){
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (subjectOptional.isEmpty()){
            throw new MessageException("Môn học không tồn tại");
        }
        return subjectOptional.get();
    }

}
