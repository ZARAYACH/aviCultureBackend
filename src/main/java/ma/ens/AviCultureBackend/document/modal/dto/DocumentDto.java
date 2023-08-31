package ma.ens.AviCultureBackend.document.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
