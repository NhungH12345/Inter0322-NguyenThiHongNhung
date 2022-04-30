package com.shop.controller;
import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.model.ProductBrand;
import com.shop.service.CategoryService;
import com.shop.service.ProductBrandService;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private CategoryService categoryService;



//    @GetMapping()
//    public ModelAndView getProductList(@ModelAttribute("products") Product product,@PageableDefault(size = 4) Pageable pageable, @RequestParam("search") Optional<String> search) {
//        Page<Product> products;
//        if (search.isPresent()) {
//            products = productService.findProductByNameContaining(search.get(), pageable);
//        } else {
//            products = productService.findAll(pageable);
//        }
//        return new ModelAndView("Admin/product/list", "products", products);
//    }
@GetMapping()
public ModelAndView getProductList(@PageableDefault(size = 4) Pageable pageable, @RequestParam("search") Optional<String> search) {
    Page<Product> products;
    if (search.isPresent()) {
        products = productService.findProductByNameContaining(search.get(), pageable);
    } else {
        products = productService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("Admin/product/list");
    modelAndView.addObject("products", products);
    return modelAndView;
}

//    @GetMapping("/home")
//    public ModelAndView getProductIndex(@PageableDefault(size = 4) Pageable pageable) {
//        Page<Product> products;
//        products = productService.findAll(pageable);
//        return new ModelAndView("home", "products", products);
//    }

    @GetMapping("/create-product")
    public ModelAndView getCreateProduct(Model model) {
        List<Category> categories = categoryService.findAll();
        List<ProductBrand> productBrands = productBrandService.findAll();
        model.addAttribute("productBrands", productBrands);
        model.addAttribute("categories", categories);
        return new ModelAndView("/Admin/product/create", "products", new Product());
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("products") Product product, @RequestParam("myfile") MultipartFile productAvatar) throws IOException {
        //productService.saveProduct(product);
        try {
            productService.addFile(product, productAvatar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/admin/product");
    }

    @GetMapping("edit-product/{id}")
    public ModelAndView showEditFormProduct(@PathVariable int id, Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<ProductBrand> productBrands = productBrandService.findAll();
        model.addAttribute("productBrands", productBrands);
        Optional<Product> product = productService.findProductById(id);
        if (product != null) {
            return new ModelAndView("/Admin/product/edit", "products", product);
        } else {
            return new ModelAndView("/error");
        }
    }
    @PostMapping("/edit-product")
    public ModelAndView updateProduct( @ModelAttribute("products") Product product) {
        productService.saveProduct(product);
        return new ModelAndView("redirect:/admin/product");
    }
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        productService.deleteProduct(id);
        return new ModelAndView("redirect:/admin/product");
    }
    @PostMapping("/delete-product")
    public ModelAndView DeleteProduct(@ModelAttribute ("products") Product product) {
//        categoryService.deleteCategory(category.getId());
        productService.deleteProduct(product.getId());
        return  new ModelAndView("redirect:/admin/product");
    }

}
