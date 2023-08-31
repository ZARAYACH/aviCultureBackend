package ma.ens.AviCultureBackend.incident.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SanctionDto(
        Long id,
        String summary,
        String description,
        String startDate,
        String endDate,

        List<Long> sanctionedUserIds
) {
}
