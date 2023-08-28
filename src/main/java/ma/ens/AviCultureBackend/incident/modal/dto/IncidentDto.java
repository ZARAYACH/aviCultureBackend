package ma.ens.AviCultureBackend.incident.modal.dto;

import java.time.LocalDateTime;

public record IncidentDto(
        Long id,
        String summary,
        String description,
        Long reporterId,
        LocalDateTime date
) {
}
