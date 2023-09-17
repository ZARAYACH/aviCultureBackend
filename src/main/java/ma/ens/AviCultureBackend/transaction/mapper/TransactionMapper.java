package ma.ens.AviCultureBackend.transaction.mapper;

import ma.ens.AviCultureBackend.transaction.model.Transaction;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;
import ma.ens.AviCultureBackend.transaction.model.dto.TransactionDto;
import ma.ens.AviCultureBackend.transaction.model.dto.TransactionProductDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TransactionMapper {

    TransactionDto toTransactionDto(Transaction transaction);

    @Mapping(source = "product.id", target = "productId")
    TransactionProductDetailDto toTransactionProductDetailDto(TransactionProductDetail transactionProductDetail);
    List<TransactionDto> toTransactionDtos(List<Transaction> allTransactions);
}
