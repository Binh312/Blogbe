package com.web.api;

import com.web.dto.LoginDto;
import com.web.dto.TokenDto;
import com.web.entity.Category;
import com.web.enums.CategoryType;
import com.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/saveOrUpdate")
    public ResponseEntity<?> save(@RequestBody Category category) {
        Category result = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/public/find-by-id")
    public ResponseEntity<?> getCategoryById(@RequestParam("id") Long id) {
        Category result = categoryService.getCategoryById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/public/find-by-type")
    public ResponseEntity<?> findByType(@RequestParam("type") CategoryType categoryType, Pageable pageable) {
        Page<Category> result = categoryService.findByType(categoryType,pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/public/get-all-and-search-category")
    public ResponseEntity<?> getTop5Category(String name, Pageable pageable){
        Page<Category> page = categoryService.getAllCategory(name,pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
