package ma.ens.AviCultureBackend.product.modal.dto;

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
