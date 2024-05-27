package com.web.repositoryCustom;

import com.web.dto.request.FilterDocumentRequest;
import com.web.entity.Document;
import com.web.entity.Subject;
import com.web.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomDocumentRepository {
    public static Specification<Document> filterDocument(FilterDocumentRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(request.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }
            if (StringUtils.hasText(request.getDescription())) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + request.getDescription() + "%"));
            }
            if (request.getCreatedDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdDate"), request.getCreatedDate()));
            }
            if (request.getActived() != null) {
                predicates.add(criteriaBuilder.equal(root.get("actived"), request.getActived()));
            }
            if (StringUtils.hasText(request.getUserName())) {
                Join<Document, User> userJoin = root.join("user");
                predicates.add(criteriaBuilder.like(userJoin.get("username"), "%" + request.getUserName() + "%"));
            }
            if (StringUtils.hasText(request.getNameSubject())) {
                Join<Document, Subject> subjectJoin = root.join("subject");
                predicates.add(criteriaBuilder.like(subjectJoin.get("nameSubject"), "%" + request.getNameSubject() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
