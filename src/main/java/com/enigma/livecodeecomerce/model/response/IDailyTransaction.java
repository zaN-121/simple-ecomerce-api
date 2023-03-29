package com.enigma.livecodeecomerce.model.response;

import com.enigma.livecodeecomerce.model.Product;

import java.time.LocalDate;

public interface IDailyTransaction {
    LocalDate getDate();
    String getBuyer();
    String getProduct();
    Integer getPrice();
    Integer getQuantity();
    Integer getSubTotal();
    Integer getGrandTotal();
    Integer getTotalTransaction();
}
