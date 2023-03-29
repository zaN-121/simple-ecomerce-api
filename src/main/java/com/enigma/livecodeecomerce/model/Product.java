package com.enigma.livecodeecomerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_product")
@NoArgsConstructor
@Getter @Setter
public class Product implements Serializable {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pruductId;
    @Column
    private String name;
    @OneToMany(mappedBy = "product")
//    @Filter(name = "active_price")
    private List<ProductPrice> productPrices;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductCategory> productCategories;

}
