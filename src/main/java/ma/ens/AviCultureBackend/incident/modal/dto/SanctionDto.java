package ma.ens.AviCultureBackend.incident.modal.dto;

import java.util.List;

public record SanctionDto(
        Long id,
        String summary,
        String description,
        String startDate,
        String endDate,

        List<Long> sanctionedUserIds
) {
}
