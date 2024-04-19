package com.web.api;

import com.web.entity.BlogLike;
import com.web.service.BlogLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog-like")
@CrossOrigin
public class BlogLikeApi {

    @Autowired
    private BlogLikeService blogLikeService;

    @GetMapping("/public/find-by-blog")
    public ResponseEntity<?> findBlogById(@RequestParam Long blogId){
        List<BlogLike> list =blogLikeService.findByBlog(blogId);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PostMapping("/all/like-or-unlike")
    public ResponseEntity<?> likeOrUnlike(@RequestParam Long blogId){
        Boolean like = blogLikeService.likeOrUnlike(blogId);
        return new ResponseEntity<>(like,HttpStatus.OK);
    }

}
