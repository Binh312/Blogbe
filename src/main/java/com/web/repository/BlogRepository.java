package com.web.repository;

import com.web.entity.Blog;
import com.web.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

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

    @Query("select b from Blog b where (b.title like %?1% or b.user.username like %?2%) and b.actived = true order by b.createdDate desc, b.createdTime desc")
    Page<Blog> searchBlogActived(String searchTitle, String userName, Pageable pageable);

    @Query("select b from Blog b " +
            "join BlogCategory bcate on b.id = bcate.blog.id " +
            "join Category c on bcate.category.id = c.id " +
            "where c.id = ?1 and b.actived = true order by b.createdDate desc, b.createdTime desc")
    Page<Blog> getBlogByCategory(Long CategoryId, Pageable pageable);

    @Query(value = "select b from Blog b order by (b.numLike + b.numComment) desc")
    Page<Blog> getTop10Blog(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 order by b.createdDate desc, b.createdTime desc")
    Page<Blog> adminSearchBlogByTitle(String searchTitle, Pageable pageable);

    @Query("select b from Blog b where b.description like ?1 order by b.createdDate desc, b.createdTime desc")
    Page<Blog> adminSearchBlogByDescription(String searchDescription, Pageable pageable);

    @Query("select b from Blog b where b.user.username like ?1 order by b.createdDate desc, b.createdTime desc")
    Page<Blog> adminSearchBlogByUsername(String searchUsername, Pageable pageable);
}
