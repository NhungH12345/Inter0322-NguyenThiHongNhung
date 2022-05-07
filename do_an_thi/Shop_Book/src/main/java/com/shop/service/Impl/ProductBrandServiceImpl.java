package com.shop.service.Impl;

import com.shop.model.ProductBrand;
import com.shop.repository.ProductBrandRepository;
import com.shop.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {
    @Autowired
    private ProductBrandRepository productBrandRepository;

    @Override
    public void deleteById(int id) {
        productBrandRepository.deleteById(id);
    }

    @Override
    public Page<ProductBrand> findAll(Pageable pageable) {
        return productBrandRepository.findAll(pageable);
    }

    @Override
    public void saveProductBrand(ProductBrand productBrand) {
        productBrandRepository.save(productBrand);
    }

    @Override
    public void deleteProductBrand(int id) {
        productBrandRepository.existsById(id);

    }

    @Override
    public Optional<ProductBrand> findProductBrandById(int id) {
        // :D
        return this.productBrandRepository.findById(id);
    }

    @Override
    public List<ProductBrand> findAll() {
        return productBrandRepository.findAll();
    }

    @Override
    public Page<ProductBrand> findProductBrandByNameContaining(String name, Pageable pageable) {
        return productBrandRepository.findProductBrandByNameContaining(name, pageable);
    }
}
