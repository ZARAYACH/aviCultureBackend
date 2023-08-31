package ma.ens.AviCultureBackend.incident.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public record IncidentDto(
        Long id,
        String summary,
        String description,
        Long reporterId,
        LocalDateTime date
) {
}
