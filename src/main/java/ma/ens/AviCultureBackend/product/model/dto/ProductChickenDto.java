package ma.ens.AviCultureBackend.product.model.dto;

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
