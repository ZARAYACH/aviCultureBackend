package ma.ens.AviCultureBackend.task.modal.dto;

import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;

import java.time.LocalDateTime;

public record BulbsReplacementTaskDto(
        Long id,
        BuildingDto building,
        Long replacedBulbCount,
        LocalDateTime date
) {
}
