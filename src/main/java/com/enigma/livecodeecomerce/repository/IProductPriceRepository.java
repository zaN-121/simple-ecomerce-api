package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductPriceRepository extends JpaRepository<ProductPrice, String> {
}
