package com.web.service;

import com.web.entity.Blog;
import com.web.entity.BlogLike;
import com.web.entity.User;
import com.web.exception.MessageException;
import com.web.repository.BlogLikeRepository;
import com.web.repository.BlogRepository;
import com.web.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogLikeService {

    @Autowired
    private BlogLikeRepository blogLikeRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserUtils userUtils;

    public void save(Long blogId){
        Optional<Blog> blog = blogRepository.findById(blogId);
        if(blog.isEmpty()){
            throw new MessageException("not found blog");
        }
        User user = userUtils.getUserWithAuthority();
        Optional<BlogLike> blogLike = blogLikeRepository.findByUserAndBlog(user.getId(), blogId);
        if(blog.isPresent()){
            return;
        }
        BlogLike b = new BlogLike();
        b.setBlog(blog.get());
        b.setUser(user);
        blogLikeRepository.save(b);
        blog.get().setNumLike(blog.get().getNumLike() + 1);
        blogRepository.save(blog.get());
    }

    public void unlike(Long id){
        Optional<BlogLike> blogLike = blogLikeRepository.findById(id);
        if(blogLike.isEmpty()){
            throw new MessageException("not found blog like");
        }
        if(blogLike.get().getUser().getId() != userUtils.getUserWithAuthority().getId()){
            throw new MessageException("Access denied");
        }
        blogLike.get().getBlog().setNumLike( blogLike.get().getBlog().getNumLike() - 1 );
        blogRepository.save(blogLike.get().getBlog());
        blogLikeRepository.deleteById(id);
    }

    public List<BlogLike> findByBlog(Long blogId){
        return blogLikeRepository.findByBlog(blogId);
    }
}
