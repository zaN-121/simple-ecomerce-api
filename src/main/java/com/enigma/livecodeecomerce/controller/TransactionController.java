package com.enigma.livecodeecomerce.controller;

import com.enigma.livecodeecomerce.exception.FobiddenException;
import com.enigma.livecodeecomerce.model.Transaction;
import com.enigma.livecodeecomerce.model.request.TransactionRequest;
import com.enigma.livecodeecomerce.model.response.IDailyTransaction;
import com.enigma.livecodeecomerce.model.response.IMonthlyTransaction;
import com.enigma.livecodeecomerce.model.response.SuccessResponse;
import com.enigma.livecodeecomerce.service.TransactionService;
import com.enigma.livecodeecomerce.util.JwtUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
@Getter @Setter
@NoArgsConstructor
public class TransactionController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/order")
    public ResponseEntity order(@RequestBody TransactionRequest transactionRequest, @RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String, String> claims = jwtUtil.getClaims(token);
        Transaction transaction = this.transactionService.create(transactionRequest, claims.get("id"));

        return ResponseEntity.ok(new SuccessResponse<Transaction>("200", "success", "berhasil order", transaction));
    }

    @GetMapping("/report/daily")
    public ResponseEntity getDailyReport(@RequestParam(name = "date", required = false) String  date, @RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String, String> claims = jwtUtil.getClaims(token);
        if (!claims.get("role").equals("admin")) {
            throw new FobiddenException("forbidden access");
        }
        List<IDailyTransaction> dailyTransactions = this.transactionService.getDailyTransaction(Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return ResponseEntity.ok(new SuccessResponse<List<IDailyTransaction>>("200", "success", "berhasil", dailyTransactions));

    }
    @GetMapping("/report/monthly")
    public ResponseEntity getDailyReport(@RequestParam(name = "start", required = false) String  start, @RequestParam(name = "end", required = false) String end, @RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String, String> claims = jwtUtil.getClaims(token);
        if (!claims.get("role").equals("admin")) {
            throw new FobiddenException("forbidden access");
        }
        List<IMonthlyTransaction> monthlyTransaction = this.transactionService.getMonthlyTransaction(
                Date.from(LocalDate.parse(start).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.parse(end).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return ResponseEntity.ok(new SuccessResponse<List<IMonthlyTransaction>>("200", "success", "berhasil", monthlyTransaction));

    }}
