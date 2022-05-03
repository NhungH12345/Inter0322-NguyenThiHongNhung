package com.shop.controller;

import com.shop.model.Category;
import com.shop.model.ProductBrand;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ModelAndView getBrandList(@PageableDefault(size = 4) Pageable pageable, @RequestParam("search") Optional<String> search) {
        Page<Category> categories;
        if (search.isPresent()) {
            categories = categoryService.findCategoryByNameContaining(search.get(), pageable);
        } else {
            categories = categoryService.findAll(pageable);
        }
        return new ModelAndView("Admin/category/list", "categories", categories);
    }


    @GetMapping("create")
    public ModelAndView getCreatePage() {
        return new ModelAndView("Admin/category/create", "category", new Category());
    }

    @PostMapping("create")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }
    @GetMapping("edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Optional<Category> categories = categoryService.findCategoryById(id);
        if (categories != null) {
            return new ModelAndView("/Admin/category/edit", "categories", categories);
        } else {
            return new ModelAndView("/error");
        }
    }
    @PostMapping("/edit-category")
    public ModelAndView updateCategory( @ModelAttribute("categories") Category category) {
        categoryService.saveCategory(category);
        return new ModelAndView("redirect:/admin/category");
    }
    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        categoryService.deleteCategory(id);
        return new ModelAndView("redirect:/admin/category");
    }
    @PostMapping("/delete-category")
    public ModelAndView DeleteCategory(@ModelAttribute ("categories") Category category) {
        categoryService.deleteCategory(category.getId());
        return  new ModelAndView("redirect:/admin/category");
    }
}