package ma.ens.AviCultureBackend.task.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BlockDto;
import ma.ens.AviCultureBackend.medical.modal.dto.DiseaseDto;

import java.time.LocalDateTime;

public record MedicationTaskDto(
        Long id,
        @NotNull
        BlockDto block,
        @NotNull
        DiseaseDto disease,
        LocalDateTime date
) {
}
