package com.web.repository;

import com.web.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.blog.id = ?1")
    List<Comment> getByBlog(Long blogId);

    @Query("select c from Comment c where c.blog.id = ?1")
    Page<Comment> findByBlog(Long BlogId, Pageable pageable);


}
