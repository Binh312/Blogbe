package com.web.repositoryCustom;

import com.web.dto.request.FilterBlogRequest;
import com.web.entity.Blog;
import com.web.entity.BlogCategory;
import com.web.entity.Category;
import com.web.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomBlogRepository {
    public static Specification<Blog> filterBlog(FilterBlogRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(request.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + request.getTitle() + "%"));
            }
            if (StringUtils.hasText(request.getDescription())) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + request.getDescription() + "%"));
            }
            if (StringUtils.hasText(request.getContent())) {
                predicates.add(criteriaBuilder.like(root.get("content"), "%" + request.getContent() + "%"));
            }
            if (request.getCreatedDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdDate"), request.getCreatedDate()));
            }
            if (request.getActived() != null) {
                predicates.add(criteriaBuilder.equal(root.get("actived"), request.getActived()));
            }
            if (StringUtils.hasText(request.getUserName())) {
                Join<Blog, User> userJoin = root.join("user");
                predicates.add(criteriaBuilder.like(userJoin.get("username"), "%" + request.getUserName() + "%"));
            }
            if (StringUtils.hasText(request.getCateName())) {
                Join<Blog, BlogCategory> blogCategoryJoin = root.join("blogCategories");
                Join<BlogCategory, Category> categoryJoin = blogCategoryJoin.join("category");
                predicates.add(criteriaBuilder.like(categoryJoin.get("name"), "%" + request.getCateName() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
