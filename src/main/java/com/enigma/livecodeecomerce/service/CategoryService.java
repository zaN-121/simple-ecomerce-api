package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.exception.NotFoundException;
import com.enigma.livecodeecomerce.model.Category;
import com.enigma.livecodeecomerce.model.request.CategoryRequest;
import com.enigma.livecodeecomerce.repository.ICategoryRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
@Transactional
@NoArgsConstructor
public class CategoryService implements IService<Category, CategoryRequest> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Category create(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        try {
            category = this.categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return category;
    }

    @Override
    public Category updateById(CategoryRequest categoryRequest, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllPagination(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Category> findByCategory(Pageable pageable, String category) {
        return null;
    }

    @Override
    public Optional<Category> findById(String id) {
        Optional<Category> optionalCategory = Optional.empty();

        try {
            optionalCategory = this.categoryRepository.findById(id);
            if (optionalCategory.isEmpty()) {
                throw new NotFoundException("Category not found");
            }
        } catch (NotFoundException e ) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return optionalCategory;
    }
}
