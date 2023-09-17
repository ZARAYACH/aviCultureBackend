package ma.ens.AviCultureBackend.vehicle.modal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record VehicleInterventionDto(
        Long id,
        String nature,
        String description,
        Integer kilometerage,
        LocalDate date,
        Float price,
        String mechanicName,
        Long vehicleId
) {
}
