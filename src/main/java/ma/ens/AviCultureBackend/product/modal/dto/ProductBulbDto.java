package ma.ens.AviCultureBackend.product.modal.dto;

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
