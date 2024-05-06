package com.web.repository;

import com.web.entity.Blog;
import com.web.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select b from Blog b where b.id = ?1 and b.actived = true")
    Optional<Blog> getBlogById(Long id);

    @Query("select b from Blog b where b.user.id = ?1 order by b.createdDate desc, b.createdTime desc")
    Page<Blog> getBlogByUser(Long userId, Pageable pageable);

    @Query("select b from Blog b where b.actived = true order by b.createdDate desc, b.createdTime desc")
    Page<Blog> getBlogActived(Pageable pageable);

    @Query("select b from Blog b where b.actived = false order by b.createdDate desc, b.createdTime desc")
    Page<Blog> getBlogUnActived(Pageable pageable);

    @Query("select b from Blog b order by  b.createdDate desc, b.createdTime desc")
    Page<Blog> findAllBlog(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 and b.actived = true")
    Page<Blog> searchBlogByTitle(String searchTitle, Pageable pageable);

    @Query("select b from BlogCategory b where b.category.id = ?1 and b.blog.actived = true")
    Page<Blog> getBlogByCategory(Long CategoryId, Pageable pageable);

    @Query("select b from Blog b where b.title like ?1")
    Page<Blog> adminSearchBlogByTitle(String search, Pageable pageable);

    @Query("select b from Blog b where b.description like ?1")
    Page<Blog> adminSearchBlogByDescription(String search, Pageable pageable);

    @Query("select b from Blog b where b.user.username like ?1")
    Page<Blog> adminSearchBlogByUsernamen(String search, Pageable pageable);
}
