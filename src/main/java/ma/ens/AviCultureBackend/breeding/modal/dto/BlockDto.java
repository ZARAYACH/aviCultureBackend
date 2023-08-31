package ma.ens.AviCultureBackend.breeding.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BlockDto(
        Long id,
        Integer dailyMortality,
        Float dailyGasCylinder,
        Float weightFirstWeek,
        Float weightEveryFeeding,
        Float weightByTheEnd,
        String foodNature,
        Float foodQuantity,
        Long buildingId
) {
}
