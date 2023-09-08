package ma.ens.AviCultureBackend.product.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;

import java.util.List;

public record ProductMedicineDto(
        String id,
        String name,
        String description,
        @NotNull(message = "storage Building can't be null")
        BuildingDto storageBuilding,
        Double unitaryPrice,
        Long quantity,
        boolean isVaccine,
        List<Long> diseaseIds
) {
}
