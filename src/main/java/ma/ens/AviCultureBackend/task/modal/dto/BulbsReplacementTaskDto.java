package ma.ens.AviCultureBackend.task.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;

import java.time.LocalDateTime;

public record BulbsReplacementTaskDto(
        Long id,
        @NotNull
        BuildingDto building,
        Long replacedBulbCount,
        LocalDateTime date
) {
}
