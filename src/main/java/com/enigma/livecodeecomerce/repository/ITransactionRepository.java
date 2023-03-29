package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.Transaction;
import com.enigma.livecodeecomerce.model.response.IDailyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, String> {
    @Query(
            value = "select " +
                    " t.date as date," +
                    " c.firstName as buyer," +
                    " p.name as product, " +
                    " pp.price as price, " +
                    " t.quantity as quantity," +
                    " SUM(t.quantity * pp.price) over (PARTITION BY t.transactionId) as subTotal," +
                    " SUM(t.quantity * pp.price) as grandTotal," +
                    " count(t.transactionId) as totalTransaction" +
                    " FROM Transaction as t left join t.user as c" +
                    " left join t.productPrice as pp" +
                    " left join pp.product as p" +
                    " where t.date = :date")
    public List<IDailyTransaction> getDailyTransaction(Date date);
}
