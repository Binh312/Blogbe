package com.web.repository;

import com.web.entity.BlogLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogLikeRepository extends JpaRepository<BlogLike, Long> {

    @Query("select b from BlogLike b where b.user.id = ?1 and b.blog.id = ?2")
    Optional<BlogLike> findByUserAndBlog(Long userId, Long blogId);

    @Query("select b from BlogLike b where b.blog.id = ?1")
    List<BlogLike> findByBlog(Long blogId);
}