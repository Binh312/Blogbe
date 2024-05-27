package com.web.repositoryCustom;

import com.web.dto.request.FilterUserRequest;
import com.web.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomUserRepository {
    public static Specification<User> filterUser(FilterUserRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(request.getUsername())) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + request.getUsername() + "%" ));
            }
            if (StringUtils.hasText(request.getFullName())) {
                predicates.add(criteriaBuilder.like(root.get("fullName"), "%" + request.getFullName() + "%" ));
            }
            if (request.getActived() != null) {
                predicates.add(criteriaBuilder.equal(root.get("actived"), request.getActived()));
            }
            if (request.getCreatedDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdDate"), request.getCreatedDate()));
            }
            if (StringUtils.hasText(request.getRole())) {
                predicates.add(criteriaBuilder.like(root.get("role"), request.getRole()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
