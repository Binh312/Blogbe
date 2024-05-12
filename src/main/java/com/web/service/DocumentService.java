package com.web.service;

import com.web.dto.request.DocumentRequest;
import com.web.dto.request.FileDto;
import com.web.entity.*;
import com.web.enums.ActiveStatus;
import com.web.exception.MessageException;
import com.web.mapper.DocumentMapper;
import com.web.repository.*;
import com.web.utils.Contains;
import com.web.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentFileRepository documentFileRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DocumentCategoryRepository documentCategoryRepository;

    public Document save(DocumentRequest request){
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if (subjectOptional.isEmpty()){
            throw new MessageException("Môn học không tồn tại");
        }
//        List<Category> categories = new ArrayList<>();
//        for (long id : request.getListCategoryId()){
//            Optional<Category> category = categoryRepository.findById(id);
//            if (category.isEmpty()){
//                throw new MessageException("Danh mục : " + id +"không tồn tại");
//            }
//            categories.add(category.get());
//        }
//
//        if (request.getLinkFiles().isEmpty()){
//            throw new MessageException("Không có file nào!");
//        }

        User user = userUtils.getUserWithAuthority();
        Document document = documentMapper.convertRequestToBlog(request);
        document.setCreatedDate(new Date(System.currentTimeMillis()));
        document.setCreatedTime(new Time(System.currentTimeMillis()));
        document.setUser(user);
        document.setImage(request.getImage());
        document.setDescription(request.getDescription());
        document.setNumView(0);
        document.setName(request.getName());
        document.setLinkFile(request.getLinkFile());
        document.setNameSubject(subjectOptional.get().getNameSubject());
        document.setSubject(subjectOptional.get());
        if(user.getRole().equals(Contains.ROLE_ADMIN) || user.getRole().equals(Contains.ROLE_DOCUMENT_MANAGER)){
            document.setActived(true);
        }

//        List<DocumentCategory> documentCategories = new ArrayList<>();
//        for (Category c: categories){
//            DocumentCategory documentCategory = new DocumentCategory();
//            documentCategory.setCategory(c);
//            documentCategory.setDocument(result);
//            documentCategories.add(documentCategory);
//        }
//        documentCategoryRepository.saveAll(documentCategories);
//
//        List<DocumentFile> documentFiles = new ArrayList<>();
//        for (FileDto fileDto: request.getLinkFiles()){
//            DocumentFile documentFile = new DocumentFile();
//            documentFile.setDocument(result);
//            documentFile.setLinkFile(fileDto.getLinkFile());
//            documentFile.setFileName(fileDto.getFileName());
//            documentFile.setFileSize(fileDto.getFileSize());
//            documentFile.setFileType(fileDto.getTypeFile());
//            documentFiles.add(documentFile);
//        }
//        documentFileRepository.saveAll(documentFiles);

        return documentRepository.save(document);
    }

    public Document update(DocumentRequest request, Long id){
        if (id == null){
            throw new MessageException("Id không được null!");
        }
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isEmpty()){
            throw new MessageException("Document không tồn tại");
        }
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if (subjectOptional.isEmpty()){
            throw new MessageException("Môn học không tồn tại");
        }

//        List<Category> categories = new ArrayList<>();
//        for (long categoryid : request.getListCategoryId()){
//            Optional<Category> category = categoryRepository.findById(categoryid);
//            if (category.isEmpty()){
//                throw new MessageException("Danh mục : " + categoryid +"không tồn tại");
//            }
//            categories.add(category.get());
//        }
        User user = userUtils.getUserWithAuthority();
        Document document = documentMapper.convertRequestToBlog(request);
        document.setCreatedDate(documentOptional.get().getCreatedDate());
        document.setCreatedTime(documentOptional.get().getCreatedTime());
        document.setUser(document.getUser());
        document.setNumView(documentOptional.get().getNumView());
        document.setLinkFile(request.getLinkFile());
        document.setNameSubject(subjectOptional.get().getNameSubject());
        document.setSubject(subjectOptional.get());
        if (documentOptional.get().getUser().getId() == user.getId() && !user.getRole().equals(Contains.ROLE_ADMIN)
                && !user.getRole().equals(Contains.ROLE_DOCUMENT_MANAGER)){
            document.setActived(false);
        } else {
            document.setActived(documentOptional.get().getActived());
        }
//        documentCategoryRepository.deleteByDocument(document.get().getId());

//        List<DocumentCategory> documentCategories = new ArrayList<>();
//        for (Category c: categories){
//            DocumentCategory documentCategory = new DocumentCategory();
//            documentCategory.setCategory(c);
//            documentCategory.setDocument(document.get());
//            documentCategories.add(documentCategory);
//        }
//        documentCategoryRepository.saveAll(documentCategories);

//        List<DocumentFile> documentFiles = new ArrayList<>();
//        for (FileDto fileDto: request.getLinkFiles()){
//            DocumentFile documentFile = new DocumentFile();
//            documentFile.setDocument(document.get());
//            documentFile.setLinkFile(fileDto.getLinkFile());
//            documentFile.setFileName(fileDto.getFileName());
//            documentFile.setFileSize(fileDto.getFileSize());
//            documentFile.setFileType(fileDto.getTypeFile());
//            documentFiles.add(documentFile);
//        }
//        documentFileRepository.saveAll(documentFiles);

        return documentRepository.save(document);
    }

    public void delete(Long id){
        Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty()){
            throw new MessageException("Document không tồn tại");
        }

        User user = userUtils.getUserWithAuthority();

        if (document.get().getUser().getId() != user.getId() && !user.getRole().equals(Contains.ROLE_ADMIN)
                && !user.getRole().equals(Contains.ROLE_DOCUMENT_MANAGER)){
            throw new MessageException("Không đủ quyền");
        }

        documentRepository.delete(document.get());
    }

    public Document findById(Long id){
        Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty()){
            throw new MessageException("Document không tồn tại");
        } else {
            document.get().setNumView(document.get().getNumView() + 1);
            return documentRepository.save(document.get());
        }
    }

    public Page<Document> getTop5Document(Pageable pageable){
        return documentRepository.getTop5Document(pageable);
    }

    public Page<Document> getDocumentUnactived(Pageable pageable){
        return documentRepository.getDocumentUnactived(pageable);
    }

    public Page<Document> searchDocumentActived(String keywords, Pageable pageable){
        if (keywords.isEmpty()) {
            return documentRepository.getDocumentActived(pageable);
        } else {
            return documentRepository.searchDocumentActived(keywords,pageable);
        }
    }

    public Page<Document> adminSearchDocument(String keywords, Pageable pageable){
        if (keywords.isEmpty()) {
            return documentRepository.getAllDocument(pageable);
        } else {
            return documentRepository.adminSearchDocument(keywords,pageable);
        }
    }

    public Page<Document> getDocumentByCategory(Long categoryId, Pageable pageable){
        return documentRepository.getDocumentByCategory(categoryId,pageable);
    }

    public ActiveStatus activeOrUnactive(Long documentId){
        Optional<Document> document = documentRepository.findById(documentId);
        if (document.isEmpty()){
            throw new MessageException("document này không tồn tại!");
        }
        if (document.get().getActived() == true){
            document.get().setActived(false);
            documentRepository.save(document.get());
            return ActiveStatus.DA_KHOA;
        } else {
            document.get().setActived(true);
            documentRepository.save(document.get());
            return ActiveStatus.DA_MO_KHOA;
        }
    }

    public Page<Document> getDocumentBySubject(Long subjectId, Pageable pageable){
        return documentRepository.getDocumentBySubject(subjectId,pageable);
    }

    public Page<Document> getDocumentByDepartment(Long departmentId, Pageable pageable){
        return documentRepository.getDocumentByDepartment(departmentId,pageable);
    }

    public Page<Document> getDocumentBySpecialize(Long specializeId, Pageable pageable){
        return documentRepository.getDocumentBySpecialize(specializeId,pageable);
    }

}
