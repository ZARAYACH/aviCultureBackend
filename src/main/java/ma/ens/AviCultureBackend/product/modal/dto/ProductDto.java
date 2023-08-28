package ma.ens.AviCultureBackend.product.modal.dto;

import ma.ens.AviCultureBackend.breeding.modal.Building;

public record ProductDto(
        String id,
        String name,
        String description,
        Building storageBuilding,
        Double unitaryPrice
) {
}
