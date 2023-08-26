package ma.ens.AviCultureBackend.product.model.dto;

public record ProductBulbDto(
        String id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        String marque,
        Integer powerInWatt
) {
}
