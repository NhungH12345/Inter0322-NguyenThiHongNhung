package com.shop.repository;
import com.shop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Page<Category> findAll(Pageable pageable);
    List<Category> findAll();
    Page<Category> findCategoryByNameContaining(String name,Pageable pageable);

}
