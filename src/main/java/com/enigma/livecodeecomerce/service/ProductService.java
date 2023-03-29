package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.exception.NotFoundException;
import com.enigma.livecodeecomerce.model.Category;
import com.enigma.livecodeecomerce.model.Product;
import com.enigma.livecodeecomerce.model.ProductCategory;
import com.enigma.livecodeecomerce.model.ProductPrice;
import com.enigma.livecodeecomerce.model.request.ProductPriceRequest;
import com.enigma.livecodeecomerce.model.request.ProductRequest;
import com.enigma.livecodeecomerce.repository.ICategoryRepository;
import com.enigma.livecodeecomerce.repository.IProductCategoryRepository;
import com.enigma.livecodeecomerce.repository.IProductPriceRepository;
import com.enigma.livecodeecomerce.repository.IProductRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter @Setter
@NoArgsConstructor
@Transactional
public class ProductService implements IProductService, IService<Product, ProductRequest>{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IProductPriceRepository productPriceRepository;
    @Autowired
    private IProductCategoryRepository productCategoryRepository;

    @Override
    public Product add(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        List<String> categorieIds = productRequest.getCategoryIds();
        ProductCategory productCategory = null;
        Optional<Category> optionalCategory = Optional.empty();
        Optional<Product> productOptional;
        try {
            product = this.productRepository.save(product);

            ProductPrice productPrice = new ProductPrice()
                    .setPrice(productRequest.getPrice())
                    .setProduct(product)
                    .setIsActive(true);

            this.productPriceRepository.save(productPrice);

            for (String categorieId : categorieIds) {
               optionalCategory = this.categoryRepository.findById(categorieId);

               if (optionalCategory.isEmpty()) {
                   throw new NotFoundException("category not found");
               }
               productCategory = new ProductCategory()
                        .setProduct(product)
                        .setCategory(optionalCategory.get());

               this.productCategoryRepository.save(productCategory);

            }

            productOptional = this.productRepository.findById(product.getPruductId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return productOptional.get();
    }

    @Override
    public Product updatePrice(ProductPriceRequest productPriceRequest) {
        Optional<Product> optionalProduct = Optional.empty();
        ProductPrice newproductPrice = new ProductPrice()
                .setPrice(productPriceRequest.getPrice())
                .setIsActive(true);
        try {
            optionalProduct = this.productRepository.findById(productPriceRequest.getProductId());
            if (optionalProduct.isEmpty()) {
                throw new NotFoundException("Product not found");
            }

            Product product = optionalProduct.get();
            ProductPrice oldProductPrice = product.getProductPrices().stream().filter((el) -> el.getIsActive() == true ).collect(Collectors.toList()).get(0);
            oldProductPrice.setIsActive(false);

            this.productPriceRepository.save(oldProductPrice);

            newproductPrice.setProduct(product);
            newproductPrice = this.productPriceRepository.save(newproductPrice);

            return product;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.findAll(pageable);
    }

    public List<Product> findAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product create(ProductRequest productRequest) {
        return null;
    }

    @Override
    public Product updateById(ProductRequest productRequest, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Page<Product> findAllPagination(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Product> findByCategory(Pageable pageable, String category) {
        return null;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }
}
