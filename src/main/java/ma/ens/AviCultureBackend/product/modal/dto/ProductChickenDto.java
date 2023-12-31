package ma.ens.AviCultureBackend.product.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BlockDto;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;

public record ProductChickenDto(
        String id,
        String name,
        String description,
        @NotNull(message = "storage Building can't be null")
        BuildingDto storageBuilding,
        Double unitaryPrice,
        Long quantity,

        @NotNull(message = "Block can't be null")
        BlockDto block
) {
}
