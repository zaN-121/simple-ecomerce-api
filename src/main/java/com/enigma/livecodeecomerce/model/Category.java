package com.enigma.livecodeecomerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_category")
@NoArgsConstructor
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private String categoryId;
    @Column
    private String name;
    @OneToMany(mappedBy = "category")
    private List<ProductCategory> productCategories;
}
