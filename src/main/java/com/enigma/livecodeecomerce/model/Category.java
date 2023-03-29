package com.enigma.livecodeecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<ProductCategory> productCategories;
}
