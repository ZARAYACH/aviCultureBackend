package ma.ens.AviCultureBackend.task.modal.dto;

import java.time.LocalDateTime;

public record MedicationTaskDto(
        Long id,
        Long blockId,
        Long diseaseId,
        LocalDateTime date

) {
}
