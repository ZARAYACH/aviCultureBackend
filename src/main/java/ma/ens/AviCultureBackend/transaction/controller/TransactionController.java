package ma.ens.AviCultureBackend.transaction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.transaction.mapper.TransactionMapper;
import ma.ens.AviCultureBackend.transaction.model.Transaction;
import ma.ens.AviCultureBackend.transaction.model.dto.TransactionDto;
import ma.ens.AviCultureBackend.transaction.service.TransactionService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_OPERATOR_VALUE})
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/add")
    public TransactionDto addTransaction(@Valid @RequestBody TransactionDto transactionDto) throws NotFoundException {
        return transactionMapper.toTransactionDto(transactionService.createTransaction(transactionDto));
    }

    @DeleteMapping("/{transactionId}/delete")
    public void deleteTransaction(@PathVariable String transactionId) throws NotFoundException {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        transactionService.deleteTransaction(transaction);
    }

}
