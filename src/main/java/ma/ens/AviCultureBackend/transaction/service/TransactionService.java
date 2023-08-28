package ma.ens.AviCultureBackend.transaction.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.Product;
import ma.ens.AviCultureBackend.product.service.ProductService;
import ma.ens.AviCultureBackend.transaction.model.CounterParty;
import ma.ens.AviCultureBackend.transaction.model.Transaction;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;
import ma.ens.AviCultureBackend.transaction.model.dto.TransactionDto;
import ma.ens.AviCultureBackend.transaction.model.dto.TransactionProductDetailDto;
import ma.ens.AviCultureBackend.transaction.repository.TransactionRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final CounterPartyService counterPartyService;
    private final ProductService productService;
    private final TransactionProductDetailService transactionProductDetailService;

    public Transaction getTransactionById(String id) throws NotFoundException {
        return transactionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction with id " + id + " Not found"));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction createTransaction(TransactionDto transactionDto) throws NotFoundException {
        Assert.notNull(transactionDto, "Provided transactionDto is null");
        Assert.notNull(transactionDto.counterParty(), "Provided Counter party is null");
        Assert.notEmpty(transactionDto.transactionProductsDetails(), "Provided Transaction Products is null");

        CounterParty counterParty;
        try {
            counterParty = counterPartyService.getCounterPartyById(transactionDto.counterParty().id());
        } catch (Exception ignored) {
            try {
                counterParty = counterPartyService.getCounterPartyByEmailAddress(transactionDto.counterParty().emailAddress());
            } catch (NotFoundException e) {
                counterParty = counterPartyService.addCounterParty(transactionDto.counterParty());
            }
        }

        Map<String, Product> products = productService.getProductsByIds(transactionDto.transactionProductsDetails().stream()
                        .filter(Objects::nonNull)
                        .map(TransactionProductDetailDto::productId).collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(Product::getId, product -> product));

        if (products.size() != transactionDto.transactionProductsDetails().stream()
                .map(TransactionProductDetailDto::productId)
                .collect(Collectors.toSet()).size()) {
            String notFoundProductsIds = transactionDto.transactionProductsDetails()
                    .stream()
                    .map(TransactionProductDetailDto::productId)
                    .filter(id -> !products.containsKey(id))
                    .collect(Collectors.joining(" , "));
            throw new NotFoundException("Products with ids [" + notFoundProductsIds + "] not found");
        }
        Transaction transaction = transactionRepo.save(Transaction.builder()
                .type(transactionDto.type())
                .counterParty(counterParty)
                .build());

        transaction.setTransactionProductsDetails(
                transactionProductDetailService.saveTransactionProductsDetails(
                        transactionDto.transactionProductsDetails().stream()
                                .map(x -> TransactionProductDetail.builder()
                                        .product(products.get(x.productId()))
                                        .transaction(transaction)
                                        .quantity(x.quantity())
                                        .expirationDate(x.expirationDate()).build())
                                .toList()));
        return transaction;
    }

    public void deleteTransaction(Transaction transaction) throws NotFoundException {
        transactionProductDetailService.deleteTransactionProductsDetailsByTransaction(transaction);
        transactionRepo.delete(transaction);
    }
}
