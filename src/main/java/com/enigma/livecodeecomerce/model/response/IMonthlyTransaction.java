package com.enigma.livecodeecomerce.model.response;

import java.time.LocalDate;

public interface IMonthlyTransaction {
    LocalDate getDate();
    String getBuyer();
    String getProductName();
    Integer getPrice();
    Integer getQuantity();
    Integer getSubTotal();
    Integer getGrandTotalPerDay();
    Integer getTotalTransactionPerDay();
    Integer getGrandTotalThisMonth();
    Integer getTotalTransactionThisMonth();
}
