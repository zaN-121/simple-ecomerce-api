package com.enigma.livecodeecomerce.controller;

import com.enigma.livecodeecomerce.exception.FobiddenException;
import com.enigma.livecodeecomerce.model.Category;
import com.enigma.livecodeecomerce.model.Product;
import com.enigma.livecodeecomerce.model.request.CategoryRequest;
import com.enigma.livecodeecomerce.model.request.ProductPriceRequest;
import com.enigma.livecodeecomerce.model.request.ProductRequest;
import com.enigma.livecodeecomerce.model.response.SuccessResponse;
import com.enigma.livecodeecomerce.service.ProductService;
import com.enigma.livecodeecomerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity create(@RequestBody ProductRequest productRequest, @RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String, String> claims = jwtUtil.getClaims(token);

        if (!claims.get("role").equals("admin")) {
            throw new FobiddenException("Fobidden access");
        }
        Product product = this.productService.add(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Product>("201", "success", "berhasil membuat category", product));
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Product> products = this.productService.findAllProduct();
        return ResponseEntity.ok(new SuccessResponse<List<Product>>("200", "success","get all", products));
    }

    @PostMapping("/price")
    public ResponseEntity updatePrice(@RequestBody ProductPriceRequest productPriceRequest, @RequestHeader("Authorization") String token){
        token = token.split(" ")[1];
        Map<String, String> claims = jwtUtil.getClaims(token);

        if (!claims.get("role").equals("admin")) {
            throw new FobiddenException("Fobidden access");
        }
        Product product = this.productService.updatePrice(productPriceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Product>("201", "success", "berhasil membuat category", product));
    }
}
