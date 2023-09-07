package ma.ens.AviCultureBackend.product.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;

public record ProductDto(
        String id,
        String name,
        String description,
        @NotNull(message = "storage Building can't be null")
        BuildingDto storageBuilding,
        Double unitaryPrice,
        Integer quantity
) {
}