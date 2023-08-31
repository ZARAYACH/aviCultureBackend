package ma.ens.AviCultureBackend.product.modal.dto;

import java.util.List;

public record ProductFoodDto (
        String id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        Long foodCategoryId,

        String remarks


) {
}
