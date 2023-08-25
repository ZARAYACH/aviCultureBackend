package ma.ens.AviCultureBackend.product.model.dto;

import ma.ens.AviCultureBackend.breeding.model.Building;

public record ProductDto(
        Long id,
        String name,
        String description,
        Building storageBuilding,
        Double unitaryPrice
) {
}
