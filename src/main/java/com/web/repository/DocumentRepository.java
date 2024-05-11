package com.web.repository;

import com.web.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select d from Document d order by d.createdDate desc, d.createdTime desc")
    Page<Document> getAllDocument(Pageable pageable);

    @Query("select d from Document d where d.actived = true order by d.numView desc")
    Page<Document> getTop5Document(Pageable pageable);

    @Query("select d from Document d where d.actived = true order by d.createdDate desc, d.createdTime desc")
    Page<Document> getDocumentActived(Pageable pageable);

    @Query("select d from Document d where d.actived = false order by d.createdDate desc, d.createdTime desc")
    Page<Document> getDocumentUnactived(Pageable pageable);

    @Query("select d from Document d where (d.name like %?1% or d.description like %?1% " +
            "or d.user.username like %?1% or d.subject.nameSubject like %?1% or d.subject.codeSubject like %?1% ) " +
            "and d.actived = true order by d.createdDate desc, d.createdTime desc")
    Page<Document> searchDocumentActived(String keywords, Pageable pageable);

    @Query("select d from DocumentCategory d where d.category.id = ?1 and d.document.actived = true")
    Page<Document> getDocumentByCategory(Long categoryId, Pageable pageable);

    @Query("select d from Document d where d.subject.id = ?1 and d.actived = true")
    Page<Document> getDocumentBySubject(Long subjectId, Pageable pageable);

    @Query("select d from Document d " +
            "join Subject sbj on d.subject.id = sbj.id " +
            "join Specialize s on sbj.specialize.id = s.id " +
            "join Department dp on s.department.id = dp.id where dp.id = ?1 and d.actived = true")
    Page<Document> getDocumentByDepartment(Long departmentId, Pageable pageable);

    @Query("select d from Document d " +
            "join Subject sbj on d.subject.id = sbj.id " +
            "join Specialize s on sbj.specialize.id = s.id where s.id = ?1 and d.actived = true")
    Page<Document> getDocumentBySpecialize(Long specializeId, Pageable pageable);

    @Query("select d from Document d where (d.name like %?1% or d.description like %?1% " +
            "or d.user.username like %?1% or d.subject.nameSubject like %?1% or d.subject.codeSubject like %?1% ) " +
            "order by d.createdDate desc, d.createdTime desc")
    Page<Document> adminSearchDocument(String keywords, Pageable pageable);
}
