package ma.ens.AviCultureBackend.product.model.dto;

import java.util.List;

public record ProductMedicineDto(
        Long id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        List<Long> diseaseIds
) {
}
