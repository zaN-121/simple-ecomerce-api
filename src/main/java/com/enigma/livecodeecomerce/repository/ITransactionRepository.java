package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.Transaction;
import com.enigma.livecodeecomerce.model.response.IDailyTransaction;
import com.enigma.livecodeecomerce.model.response.IMonthlyTransaction;
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
                    " sum(t.quantity * pp.price) over (partition by t.date) as grandTotalToday," +
                    " count(t.transactionId) as totalTransaction," +
                    " count(t.transactionId) over (partition by t.date) as totalTransactionToday" +
                    " FROM Transaction as t left join t.user as c" +
                    " left join t.productPrice as pp" +
                    " left join pp.product as p" +
                    " where t.date = :date" +
                    " group by t.date, c.firstName, p.name, pp.price, t.quantity, t.transactionId")
    public List<IDailyTransaction> getDailyTransaction(Date date);

    @Query(
            value = "select " +
                    " t.date as date," +
                    " c.firstName as buyer," +
                    " p.name as productName, " +
                    " pp.price as price, " +
                    " t.quantity as quantity," +
                    " SUM(t.quantity * pp.price) over (PARTITION BY t.transactionId) as subTotal," +
                    " sum(t.quantity * pp.price) over (partition by t.date) as grandTotalPerDay," +
                    " count(t.transactionId) over (partition by t.date) as totalTransactionPerDay," +
                    " sum(t.quantity * pp.price) over () as grandTotalThisMonth," +
                    " count(t.transactionId) over () as totalTransactionThisMonth" +
                    " FROM Transaction as t left join t.user as c" +
                    " left join t.productPrice as pp" +
                    " left join pp.product as p" +
                    " where t.date between :start and :end" +
                    " group by t.date, c.firstName, p.name, pp.price, t.quantity, t.transactionId")
    public List<IMonthlyTransaction> getMonthlyTransaction(Date start, Date end);
}
