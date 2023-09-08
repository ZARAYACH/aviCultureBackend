package ma.ens.AviCultureBackend.product.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

public record ProductStrawBalesDto(
        String id,
        String name,
        String description,
        @NotNull(message = "storage Building can't be null")
        BuildingDto storageBuilding,
        Double unitaryPrice,
        Long quantity,
        String type
) {
}
