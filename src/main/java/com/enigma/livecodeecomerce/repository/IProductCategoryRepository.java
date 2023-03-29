package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductCategoryRepository extends JpaRepository<ProductCategory, String> {
}
