package com.web.customrepository;

import com.web.dto.request.BlogRequest;
import com.web.entity.Blog;
import com.web.utils.CriteriaBuilderUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomBlogRepository {
    public static Specification<Blog> filter(String keywords) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // root: bảng trong database
            if(StringUtils.hasText(keywords)){
                predicates.add(CriteriaBuilderUtil.createPredicateForSearchInsensitive(root, criteriaBuilder, keywords,
                        "title", "description", "content"));
            }

//            if(StringUtils.hasText(request.getKeyword())){
//                predicates.add(criteriaBuilder.like(root.get("title"), "%" + request.getKeyword() + "%"));
//            }

            // query: điều kiện
            query.orderBy(
                    criteriaBuilder.desc(root.get("createdDate")),
                    criteriaBuilder.desc(root.get("createdTime"))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
