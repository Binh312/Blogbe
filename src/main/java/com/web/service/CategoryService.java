package com.web.service;

import com.web.entity.BlogCategory;
import com.web.entity.Category;
import com.web.enums.CategoryType;
import com.web.exception.MessageException;
import com.web.repository.BlogCategoryRepository;
import com.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    public Category saveOrUpdate(Category category){
        if( categoryRepository.findByName(category.getName()).isPresent() && category.getId() == null){
            throw new MessageException("Tên danh mục đã tồn tại");
        }
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        List<BlogCategory> blogCategories = blogCategoryRepository.findBlogCategoriesByCategoryId(id);
        blogCategoryRepository.deleteAll(blogCategories);

        categoryRepository.deleteById(id);
    }

    public Page<Category> findByType(CategoryType categoryType, Pageable pageable){
        return categoryRepository.findByType(categoryType, pageable);
    }

    public Category getCategoryById(Long id){
        Optional<Category> category = categoryRepository.getCategoryById(id);
        if(category.isEmpty()){
            throw new MessageException("Không tìm thấy danh mục");
        }
        return category.get();
    }

    public Page<Category> getAllCategory(String name, Pageable pageable){
        if (name.isEmpty()){
            return categoryRepository.getAllCategory(pageable);
        } else {
            return categoryRepository.searchCategoriesByName(name,pageable);
        }
    }

    public Page<Category> getTop5Category(Pageable pageable){
        return categoryRepository.getTop5Category(pageable);
    }
}
