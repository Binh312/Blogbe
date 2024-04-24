package com.web.repository;

import com.web.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select d from Document d")
    Page<Document> findAll(Pageable pageable);

    @Query("select d from Document d where d.actived = true")
    Page<Document> getDocumentActived(Pageable pageable);

    @Query("select d from Document d where d.actived = false ")
    Page<Document> getDocumentUnactived(Pageable pageable);

    @Query("select d from Document d where d.name = ?1 and d.actived = true")
    Page<Document> searchDocumentByName(String name, Pageable pageable);

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
}
