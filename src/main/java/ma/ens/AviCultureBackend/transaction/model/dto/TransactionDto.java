package ma.ens.AviCultureBackend.transaction.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.transaction.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public record TransactionDto(
        String id,
        @NotNull(message = "Transaction type can't be null")
        Transaction.Type type,
        @NotNull(message = "counter party can't be null")
        CounterPartyDto counterParty,
        @NotNull(message = "transaction Product Detail can't be null")
        @NotEmpty(message = "transaction Product Detail can't be empty")
        List<TransactionProductDetailDto> transactionProductsDetails,
        LocalDateTime timeStamp
) {
}
