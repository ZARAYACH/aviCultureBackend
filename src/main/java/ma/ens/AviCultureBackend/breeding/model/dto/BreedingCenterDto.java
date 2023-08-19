package ma.ens.AviCultureBackend.breeding.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record BreedingCenterDto(
        Long id,
        @NotNull(message = "breeding center name can't be null")
        @NotBlank(message = "breeding center name can't be empty")
        String name,
        @NotNull(message = "breeding center address can't be null")
        @NotBlank(message = "breeding center address can't be empty")
        String address
) {
}
