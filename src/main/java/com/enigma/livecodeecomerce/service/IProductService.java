package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.model.Product;
import com.enigma.livecodeecomerce.model.request.ProductPriceRequest;
import com.enigma.livecodeecomerce.model.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    public Product add(ProductRequest productRequest);
    public Product updatePrice(ProductPriceRequest productPriceRequest);
    public Page<Product> findAll(Pageable pageable);
}
