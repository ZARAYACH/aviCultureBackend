package ma.ens.AviCultureBackend.product.model.dto;

public record ProductChickenDto(
        Long id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        Long blockId
) {
}
