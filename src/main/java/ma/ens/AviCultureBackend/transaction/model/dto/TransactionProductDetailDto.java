package ma.ens.AviCultureBackend.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TransactionProductDetailDto(
        String id,
        String productId,
        Long quantity,
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime expirationDate
) {
}
