package com.enigma.livecodeecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "tb_product_price")
@NoArgsConstructor
@Getter @Setter
@FilterDef(name = "active_price", defaultCondition = "isActive = true")
@Accessors(chain = true)
@Where(clause = "is_active = true") // ini buat filter yang memang harga active doangk, alhamdulillah dapat ilmu lagi
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_price_id")
    private String productPriceId;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Product product;
    @Column
    private Integer price;
    @Column(name = "is_active", columnDefinition = "boolean default false")
    private Boolean isActive;
}
