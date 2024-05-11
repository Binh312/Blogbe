package com.web.repository;

import com.web.entity.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {

    @Modifying
    @Transactional
    @Query("delete from BlogCategory b where b.blog.id = ?1")
    public int deleteByBlog(Long blogId);

    @Query("select bcate from BlogCategory bcate where bcate.category.id = ?1")
    List<BlogCategory> findBlogCategoriesByCategoryId(Long categoryId);

    List<BlogCategory> findAllByBlogId(Long blogId);
}
