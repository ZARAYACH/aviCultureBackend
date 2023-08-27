package ma.ens.AviCultureBackend.document.modal.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DocumentDto (
        String id,
        Long typeId,
        String name,
        String description,
        LocalDateTime inserterAt,
        LocalDateTime expirationDate,
        List<Long> vehicleIds
){
}
