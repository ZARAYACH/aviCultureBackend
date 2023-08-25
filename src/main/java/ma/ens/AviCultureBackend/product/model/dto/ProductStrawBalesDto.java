package ma.ens.AviCultureBackend.product.model.dto;

public record ProductStrawBalesDto(
        Long id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        String type
) {
}
