package ma.ens.AviCultureBackend.breeding.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.Intervention;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public record InterventionDto(
        Long id,
        @NotNull(message = "Intervention nature can't be null")
        Intervention.Nature nature,
        LocalDateTime beginningDate,
        LocalDateTime endDate
) {
}
