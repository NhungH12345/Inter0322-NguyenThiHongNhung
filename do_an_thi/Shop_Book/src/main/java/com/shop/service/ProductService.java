package com.shop.service;
import com.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    void saveProduct(Product product);
    void deleteProduct(int id);
    Optional<Product> findProductById(int id);
    Page<Product> findProductByNameContaining(String name,Pageable pageable);
    void addFile(Product productsEnity, MultipartFile productAvatar) throws IOException;
    void editFile(Product productsEnity, MultipartFile productAvatar) throws IOException;

    List<Product>findAll();

    Page<Product> searchByName(String nameProduct, Pageable pageable);
}