package ma.ens.AviCultureBackend.breeding.model;

public record BlockDto(
        Long id,
        Integer dailyMortality,
        Float dailyGasCylinder,
        Float weightFirstWeek,
        Float weightEveryFeeding,
        Float weightByTheEnd,

        String foodNature,
        String foodQuantity,
        Building building
) {
}
