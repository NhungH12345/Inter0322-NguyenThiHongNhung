package com.shop.service;

import com.shop.model.ProductBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductBrandService {
    @Query("delete from ProductBrand WHERE Product.productBrand.id =?1")
    void deleteById(int id);
    Page<ProductBrand> findAll(Pageable pageable);
    void saveProductBrand(ProductBrand productBrand);
    void deleteProductBrand(int id);
    Optional<ProductBrand> findProductBrandById(int id);
    List<ProductBrand> findAll();
    Page<ProductBrand> findProductBrandByNameContaining(String name,Pageable pageable);

}

