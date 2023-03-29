package com.enigma.livecodeecomerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_transaction")
@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    @Column(columnDefinition = "date default current_date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column
    private Integer quantity;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private ProductPrice productPrice;

}
