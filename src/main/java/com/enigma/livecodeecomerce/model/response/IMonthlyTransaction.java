package com.enigma.livecodeecomerce.model.response;

import java.time.LocalDate;

public interface IMonthlyTransaction {
    LocalDate getDate();
    String getBuyer();
    String getProductName();
    Integer getProductPrice();
    Integer getQuantity();
    Integer getSubTotal();
    Integer getGrandTotal();
    Integer getTotalTransaction();
}
