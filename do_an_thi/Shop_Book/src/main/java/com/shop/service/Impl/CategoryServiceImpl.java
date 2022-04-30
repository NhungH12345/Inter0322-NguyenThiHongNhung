package com.shop.service.Impl;

import com.shop.model.Category;
import com.shop.repository.CategoryRepository;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public void saveCategory(Category category) {
    categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public Optional<Category> findCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findCategoryByNameContaining(String name, Pageable pageable) {
        return categoryRepository.findCategoryByNameContaining(name, pageable);
    }
}
