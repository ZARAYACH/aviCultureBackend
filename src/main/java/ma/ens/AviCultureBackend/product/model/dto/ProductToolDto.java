package ma.ens.AviCultureBackend.product.model.dto;

public record ProductToolDto(
        String id,
        String name,
        String description,
        Long storageBuildingId,
        Double unitaryPrice,
        String usedFor,
        Long toolCategorieId
) {
}
