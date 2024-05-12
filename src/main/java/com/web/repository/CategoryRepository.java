package com.web.repository;

import com.web.entity.Category;
import com.web.enums.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name like ?1")
    Optional<Category> findByName(String name);

    @Query("select c from Category c where c.id = ?1")
    Optional<Category> getCategoryById(Long categoryId);

    @Query("select c from Category c where c.categoryType = ?1")
    Page<Category> findByType(CategoryType type, Pageable pageable);

    @Query("select c from Category c order by c.numBlog desc")
    Page<Category> getAllCategory(Pageable pageable);

    @Query("select c from Category c where c.name like %?1%")
    Page<Category> searchCategoriesByName(String name, Pageable pageable);
}
