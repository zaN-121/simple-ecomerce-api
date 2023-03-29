package com.enigma.livecodeecomerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IService<T, U> {
    public T create(U u);
    public T updateById(U u, String id);
    public void deleteById(String id);
    public Page<T> findAllPagination(Pageable pageable);
    public Page<T> findByCategory(Pageable pageable, String category);
    public Optional<T> findById(String id);
}
