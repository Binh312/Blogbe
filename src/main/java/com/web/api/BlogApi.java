package com.web.api;

import com.web.dto.request.BlogRequest;
import com.web.dto.request.FilterBlogRequest;
import com.web.entity.Blog;
import com.web.enums.ActiveStatus;
import com.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin
public class BlogApi {

    @Autowired
    private BlogService blogService;

    @PostMapping("/all/save-update")
    public ResponseEntity<?> saveOrUpdate(@RequestBody BlogRequest blogRequest) {
        Blog blog = blogService.save(blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @DeleteMapping("/all/delete")
    public ResponseEntity<?> delete(@RequestParam Long blogId) {
        String mess = blogService.deleteBlog(blogId);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    @GetMapping("/all/get-blog-by-id")
    public ResponseEntity<?> getBlogById(@RequestParam Long id) {
        Blog blog = blogService.getBlogById(id);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-all-active")
    public ResponseEntity<?> getBlogActived(@RequestParam(required = false) String keywords, Pageable pageable) {
        Page<Blog> page = blogService.getBlogActived(keywords, pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-all-unactive")
    public ResponseEntity<?> getBlogUnActived(@RequestParam(required = false) String keywords, Pageable pageable) {
        Page<Blog> page = blogService.getBlogUnActived(keywords, pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-blog-by-category")
    public ResponseEntity<?> getBlogByCategory(@RequestParam Long categoryId, Pageable pageable) {
        Page<Blog> page = blogService.getBlogByCategory(categoryId, pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-top10-blog")
    public ResponseEntity<?> getTop10Blog(Pageable pageable) {
        Page<Blog> blogs = blogService.getTop10Blog(pageable);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping("/blog-manager/active-or-unactive")
    public ResponseEntity<?> activeOrUnactive(@RequestParam Long blogId) {
        ActiveStatus activeStatuse = blogService.activeOrUnactive(blogId);
        return new ResponseEntity<>(activeStatuse, HttpStatus.CREATED);
    }

    @GetMapping("/all/get-blog-by-user")
    public ResponseEntity<?> getBlogByUser(@RequestParam Long userId, Pageable pageable) {
        Page<Blog> page = blogService.getBlogByUser(userId, pageable);
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @GetMapping("/blog-manager/filter")
    public ResponseEntity<?> filterBlog(FilterBlogRequest request, Pageable pageable){
        Page<Blog> page = blogService.filterBlog(request,pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
