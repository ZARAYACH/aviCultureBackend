package ma.ens.AviCultureBackend.product.model.dto;

public record ProductBulbDto(
        Long id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        Integer quantity,
        String marque,
        Integer powerInWatt
) {
}
