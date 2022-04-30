package com.shop.repository;
import com.shop.model.ProductBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {

    Page<ProductBrand> findAll(Pageable pageable);

    List<ProductBrand> findAll();

    Page<ProductBrand> findProductBrandByNameContaining(String name, Pageable pageable);
}
