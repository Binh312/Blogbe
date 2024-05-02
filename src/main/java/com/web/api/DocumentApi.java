package com.web.api;

import com.web.dto.request.DocumentRequest;
import com.web.entity.Document;
import com.web.enums.ActiveStatus;
import com.web.repository.DocumentRepository;
import com.web.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
@CrossOrigin
public class DocumentApi {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/all/save")
    public ResponseEntity<?> saveDocument(@RequestBody DocumentRequest documentRequest){
        Document document = documentService.save(documentRequest);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @PostMapping("/all/update")
    public ResponseEntity<?> updateDocument(@RequestBody DocumentRequest documentRequest,@RequestParam Long Id){
        Document document = documentService.update(documentRequest, Id);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @GetMapping("/document-manager/find-all")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Document> document = documentService.findAll(pageable);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @GetMapping("/document-manager/get-document-unactived")
    public ResponseEntity<?> getDocumentUnactived(Pageable pageable){
        Page<Document> document = documentService.getDocumentUnactived(pageable);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @DeleteMapping("/all/delete")
    public void deleteDocument(@RequestParam Long documentId){
        documentService.delete(documentId);
    }

    @GetMapping("/all/findbyid")
    public ResponseEntity<?> findById(@RequestParam Long id){
        Document document = documentService.findById(id);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-all-document")
    public ResponseEntity<?> getDocumentActived(Pageable pageable){
        Page<Document> documentPage = documentService.getDocumentActived(pageable);
        return new ResponseEntity<>(documentPage, HttpStatus.CREATED);
    }

    @GetMapping("/public/search-document-by-name")
    public ResponseEntity<?> searchDocumentByName(@RequestParam String name, Pageable pageable){
        Page<Document> documentPage = documentService.searchDocumentByName(name,pageable);
        return new ResponseEntity<>(documentPage, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-document-by-category")
    public ResponseEntity<?> getDocumentByCategory(@RequestParam Long categoryId, Pageable pageable){
        Page<Document> documentPage = documentService.getDocumentByCategory(categoryId,pageable);
        return new ResponseEntity<>(documentPage, HttpStatus.CREATED);
    }

    @PostMapping("/document-manager/active-or-unacative")
    public ResponseEntity<?> activeOrUnactive(@RequestParam Long documentId){
        ActiveStatus activeStatuse = documentService.activeOrUnactive(documentId);
        return new ResponseEntity<>(activeStatuse, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-document-by-department")
    public ResponseEntity<?> getDocumentByDepartment(@RequestParam Long departmentId,Pageable pageable){
        Page<Document> documentPage = documentService.getDocumentByDepartment(departmentId,pageable);
        return new ResponseEntity<>(documentPage, HttpStatus.OK);
    }

    @GetMapping("/public/get-document-by-specialize")
    public ResponseEntity<?> getDocumentBySpecialize(@RequestParam Long specializeId,Pageable pageable){
        Page<Document> documentPage = documentService.getDocumentBySpecialize(specializeId,pageable);
        return new ResponseEntity<>(documentPage, HttpStatus.OK);
    }

    @GetMapping("/public/get-document-by-subject")
    public ResponseEntity<?> getDocumentBySubject(@RequestParam Long subjectId,Pageable pageable){
        Page<Document> documentPage = documentService.getDocumentBySubject(subjectId,pageable);
        return new ResponseEntity<>(documentPage, HttpStatus.OK);
    }
}
