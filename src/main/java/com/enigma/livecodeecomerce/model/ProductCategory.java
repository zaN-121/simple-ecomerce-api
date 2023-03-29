package com.enigma.livecodeecomerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tb_product_category")
@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
public class ProductCategory {
    @Id
    @Column(name = "product_price_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productPriceId;
    @ManyToOne
    @JoinColumn
    private Product product;
    @ManyToOne
    @JoinColumn
    private Category category;
}
