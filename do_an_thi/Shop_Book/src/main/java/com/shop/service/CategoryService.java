package com.shop.service;

import com.shop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<Category>findAll(Pageable pageable);
    void saveCategory(Category category);
    void deleteCategory(int id);
    Optional<Category> findCategoryById(int id);
    List<Category> findAll();
    Page<Category> findCategoryByNameContaining(String name,Pageable pageable);

}
