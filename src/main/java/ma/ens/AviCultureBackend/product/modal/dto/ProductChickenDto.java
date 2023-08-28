package ma.ens.AviCultureBackend.product.modal.dto;

public record ProductChickenDto(
        String id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        Long blockId
) {
}
