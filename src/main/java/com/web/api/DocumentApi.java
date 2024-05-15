package com.web.api;

import com.web.dto.request.DocumentRequest;
import com.web.dto.response.DocumentResponse;
import com.web.dto.response.SubjectResponse;
import com.web.entity.Blog;
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

    @PostMapping("/all/save-update")
    public ResponseEntity<?> saveDocument(@RequestBody DocumentRequest documentRequest){
        Document document = documentService.save(documentRequest);
        DocumentResponse documentResponse = DocumentResponse.converterDocumentToDocumentResponse(document);
        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
    }

//    @PostMapping("/all/update")
//    public ResponseEntity<?> updateDocument(@RequestBody DocumentRequest documentRequest,@RequestParam Long Id){
//        Document document = documentService.update(documentRequest, Id);
//        DocumentResponse documentResponse = DocumentResponse.converterDocumentToDocumentResponse(document);
//        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
//    }

    @DeleteMapping("/all/delete")
    public void deleteDocument(@RequestParam Long documentId){
        documentService.delete(documentId);
    }

    @PostMapping("/document-manager/active-or-unacative")
    public ResponseEntity<?> activeOrUnactive(@RequestParam Long documentId){
        ActiveStatus activeStatuse = documentService.activeOrUnactive(documentId);
        return new ResponseEntity<>(activeStatuse, HttpStatus.CREATED);
    }

    @GetMapping("/public/findbyid")
    public ResponseEntity<?> findById(@RequestParam Long id){
        Document document = documentService.findById(id);
        DocumentResponse documentResponse = DocumentResponse.converterDocumentToDocumentResponse(document);
        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-top5-document")
    public ResponseEntity<?> getTop5Document(Pageable pageable){
        Page<Document> document = documentService.getTop5Document(pageable);
        Page<DocumentResponse> documentResponses = document.map(DocumentResponse::converterDocumentToDocumentResponse);
        return new ResponseEntity<>(documentResponses, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-all-active")
    public ResponseEntity<?> getAllAndSearchDocumentActived(@RequestParam(required = false) String keywords,
                                                            @RequestParam(required = false) Long subjectId,
                                                            @RequestParam(required = false) Long userId,Pageable pageable){
        Page<Document> document = documentService.getAllActived(keywords,subjectId,userId,pageable);
        Page<DocumentResponse> documentResponses = document.map(DocumentResponse::converterDocumentToDocumentResponse);
        return new ResponseEntity<>(documentResponses, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-all-unactive")
    public ResponseEntity<?> getAllAndSearchDocumentUnActived(@RequestParam(required = false) String keywords,
                                                              @RequestParam(required = false) Long subjectId,
                                                              Pageable pageable){
        Page<Document> document = documentService.getAllUnactived(keywords,subjectId,pageable);
        Page<DocumentResponse> documentResponses = document.map(DocumentResponse::converterDocumentToDocumentResponse);
        return new ResponseEntity<>(documentResponses, HttpStatus.CREATED);
    }

    @PostMapping("/all/save-or-unsave-document")
    public ResponseEntity<?> saveOrUnSaveDocument(@RequestParam Long documentId){
        String mess = documentService.saveOrUnSaveDocument(documentId);
        return new ResponseEntity<>(mess, HttpStatus.CREATED);
    }

//    @GetMapping("/public/get-document-by-category")
//    public ResponseEntity<?> getDocumentByCategory(@RequestParam Long categoryId, Pageable pageable){
//        Page<Document> document = documentService.getDocumentByCategory(categoryId,pageable);
//        Page<DocumentResponse> documentResponses = document.map(DocumentResponse::converterDocumentToDocumentResponse);
//        return new ResponseEntity<>(documentResponses, HttpStatus.CREATED);
//    }


//    @GetMapping("/public/get-document-by-department")
//    public ResponseEntity<?> getDocumentByDepartment(@RequestParam Long departmentId,Pageable pageable){
//        Page<Document> documentPage = documentService.getDocumentByDepartment(departmentId,pageable);
//        return new ResponseEntity<>(documentPage, HttpStatus.OK);
//    }
//
//    @GetMapping("/public/get-document-by-specialize")
//    public ResponseEntity<?> getDocumentBySpecialize(@RequestParam Long specializeId,Pageable pageable){
//        Page<Document> documentPage = documentService.getDocumentBySpecialize(specializeId,pageable);
//        return new ResponseEntity<>(documentPage, HttpStatus.OK);
//    }

}
