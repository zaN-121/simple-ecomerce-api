package com.enigma.livecodeecomerce.controller;

import com.enigma.livecodeecomerce.exception.FobiddenException;
import com.enigma.livecodeecomerce.model.Category;
import com.enigma.livecodeecomerce.model.request.CategoryRequest;
import com.enigma.livecodeecomerce.model.response.SuccessResponse;
import com.enigma.livecodeecomerce.service.CategoryService;
import com.enigma.livecodeecomerce.util.JwtUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@Getter
@Setter
@NoArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity create(@RequestBody CategoryRequest categoryRequest, @RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String, String> claims = jwtUtil.getClaims(token);

        if (!claims.get("role").equals("admin")) {
            throw new FobiddenException("Fobidden access");
        }
        Category category = this.categoryService.create(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Category>("201", "success", "berhasil membuat category", category));
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Category> categories = this.categoryService.findAll();
        return ResponseEntity.ok(new SuccessResponse<List<Category>>("200", "success","get all", categories));
    }
}
