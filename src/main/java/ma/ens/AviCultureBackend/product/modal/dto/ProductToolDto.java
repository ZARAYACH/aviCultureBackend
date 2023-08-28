package ma.ens.AviCultureBackend.product.modal.dto;

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
