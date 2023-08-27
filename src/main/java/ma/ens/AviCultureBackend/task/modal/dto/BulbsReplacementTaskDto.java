package ma.ens.AviCultureBackend.task.modal.dto;

import java.time.LocalDateTime;

public record BulbsReplacementTaskDto(
        Long id,
        Long buildingId,
        Long replacedBulbCount,
        LocalDateTime date
) {
}
