package ma.ens.AviCultureBackend.product.model.dto;

public record ProductGasCylinderDto(
        String id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        String type
) {
}
