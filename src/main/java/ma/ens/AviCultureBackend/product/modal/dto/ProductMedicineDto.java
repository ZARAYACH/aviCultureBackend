package ma.ens.AviCultureBackend.product.modal.dto;

import java.util.List;

public record ProductMedicineDto(
        String id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        boolean isVaccine,
        List<Long> diseaseIds
) {
}
