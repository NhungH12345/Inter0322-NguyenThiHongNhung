package com.shop.repository;

import com.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    Page<Product> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name LIKE ?1")
    Page<Product> findProductByNameContaining(String name,Pageable pageable);
    List<Product> findAll();
    @Query(value = "SELECT * FROM product where product.category_id_category = :id",nativeQuery = true)
    List<Product>findAllCategoryId(Integer id);
    @Query(value="SELECT * FROM product p WHERE p.name like concat('%',?1,'%') ",
            countQuery="SELECT count(*) FROM product p WHERE p.name like concat('%',?1,'%') ",
            nativeQuery=true)
    Page<Product> searchByName(String nameProduct, Pageable pageable);

}
