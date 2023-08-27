package ma.ens.AviCultureBackend.task.modal.dto;

import ma.ens.AviCultureBackend.task.modal.VaccinationTask;

import java.time.LocalDateTime;

public record VaccinationTaskDto(
        Long id,
        VaccinationTask.Type type,
        Long blockId,
        Long diseaseId,
        LocalDateTime date

) {
}
