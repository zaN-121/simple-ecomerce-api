package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, String> {
}
