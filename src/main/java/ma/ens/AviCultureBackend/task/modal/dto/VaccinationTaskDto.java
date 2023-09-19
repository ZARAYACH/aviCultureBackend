package ma.ens.AviCultureBackend.task.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BlockDto;
import ma.ens.AviCultureBackend.medical.modal.dto.DiseaseDto;
import ma.ens.AviCultureBackend.task.modal.VaccinationTask;

import java.time.LocalDateTime;

public record VaccinationTaskDto(
        Long id,
        VaccinationTask.Type type,
        @NotNull
        BlockDto block,
        @NotNull
        DiseaseDto disease,
        LocalDateTime date

) {
}
