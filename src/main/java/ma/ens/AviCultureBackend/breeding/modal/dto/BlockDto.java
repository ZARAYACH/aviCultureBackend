package ma.ens.AviCultureBackend.breeding.modal.dto;

public record BlockDto(
        Long id,
        Integer dailyMortality,
        Float dailyGasCylinder,
        Float weightFirstWeek,
        Float weightEveryFeeding,
        Float weightByTheEnd,
        String foodNature,
        String foodQuantity,
        Long buildingId
) {
}
