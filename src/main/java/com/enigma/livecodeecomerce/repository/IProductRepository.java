package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, String > {
}
