package ma.ens.AviCultureBackend.breeding.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.Building;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BuildingDto(
        Long id,
        @NotNull(message = "breeding building name can't be null")
        @NotBlank(message = "breeding building name can't be empty")
        String name,
        @NotNull(message = "building Nature Can't be null")
        Building.Nature nature,
        Float humidityRate,
        @NotNull(message = "breeding building surface can't be null")
        Float surface,
        Float temperature,
        Building.BreedingBuildingState state,
        @NotNull(message = "breeding center id can't be null")
        Long breedingCenterId
) {
}
