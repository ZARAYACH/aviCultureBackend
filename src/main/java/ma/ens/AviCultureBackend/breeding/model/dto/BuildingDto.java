package ma.ens.AviCultureBackend.breeding.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.model.Building;

public record BuildingDto(
        Long id,
        @NotNull(message = "breeding building name can't be null")
        @NotBlank(message = "breeding building name can't be empty")
        String name,
        Float humidityRate,
        @NotNull(message = "breeding building surface can't be null")
        Float surface,
        Float temperature,
        Building.BreedingBuildingState state,
        Long breedingCenterId
) {
}
