package ma.ens.AviCultureBackend.product.modal.dto;

import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;

import java.util.List;

public record ProductFoodDto (
        String id,
        String name,
        String description,
        @NotNull(message = "storage Building can't be null")
        BuildingDto storageBuilding,
        Double unitaryPrice,
        Long quantity,
        @NotNull(message = "food category can't be null")
        FoodCategoryDto foodCategory,
        String remarks


) {
}
