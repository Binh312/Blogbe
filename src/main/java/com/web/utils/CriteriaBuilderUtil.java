package com.web.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CriteriaBuilderUtil {
    public static <T> Predicate createPredicateForArrayColumn(Root<T> root,
                                                              CriteriaBuilder criteriaBuilder,
                                                              String fieldName,
                                                              Set<? extends Object> values) {
        List<Predicate> predicates = new ArrayList<>();
        for (Object value : values) {
            predicates.add(criteriaBuilder.isNotNull(criteriaBuilder.function("array_position",
                    String.class, root.get(fieldName), criteriaBuilder.literal(value))));
        }
        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    public static <T> Predicate createPredicateForSearchInsensitive(Root<T> root,
                                                                   CriteriaBuilder criteriaBuilder,
                                                                   String keyword,
                                                                   String... fieldNames) {
        List<Predicate> predicates = new ArrayList<>();
        if (fieldNames != null && fieldNames.length > 0) {
            for (String fieldName : fieldNames) {
                predicates.add(criteriaBuilder
                        .like(criteriaBuilder.lower(root.get(fieldName)), "%" + keyword.toLowerCase(Locale.ROOT) + "%"));
            }
        }
        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
