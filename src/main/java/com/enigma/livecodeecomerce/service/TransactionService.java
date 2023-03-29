package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.exception.NotFoundException;
import com.enigma.livecodeecomerce.model.ProductPrice;
import com.enigma.livecodeecomerce.model.Transaction;
import com.enigma.livecodeecomerce.model.User;
import com.enigma.livecodeecomerce.model.request.TransactionRequest;
import com.enigma.livecodeecomerce.model.response.IDailyTransaction;
import com.enigma.livecodeecomerce.repository.IProductPriceRepository;
import com.enigma.livecodeecomerce.repository.ITransactionRepository;
import com.enigma.livecodeecomerce.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
@Getter @Setter
public class TransactionService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IProductPriceRepository productPriceRepository;
    public Transaction create(TransactionRequest transactionRequest, String userId) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        Optional<User> optionalUser = Optional.empty();
        Optional<ProductPrice> optionalProductPrice = Optional.empty();

        try {
            optionalUser = this.userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                throw new NotFoundException("User Not Found");
            }

            optionalProductPrice = this.productPriceRepository.findById(transactionRequest.getProductPriceId());

            if (optionalProductPrice.isEmpty()) {
                throw new NotFoundException("Product Not Found");
            }

            transaction
                    .setUser(optionalUser.get())
                    .setProductPrice(optionalProductPrice.get());

            transaction = this.transactionRepository.save(transaction);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return transaction;
    }

    public List<IDailyTransaction> getDailyTransaction(Date date) {
        return this.transactionRepository.getDailyTransaction(date);
    }
}
