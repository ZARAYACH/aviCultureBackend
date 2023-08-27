package ma.ens.AviCultureBackend.vehicle.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record VehicleDto(
        Long id,
        @NotNull(message = "Vehicle type can't be null")
        @NotBlank(message = "Vehicle type can't be empty")
        String type,
        @NotNull(message = "Vehicle marque can't be null")
        @NotBlank(message = "Vehicle marque can't be empty")
        String marque,
        @NotNull(message = "Vehicle module can't be null")
        @Min(value = 1900, message = "module can't be less then 1900")
        Integer module,
        @NotNull(message = "Vehicle licence plate can't be null")
        @NotBlank(message = "Vehicle licence plate can't be empty")
        String licencePlate,
        @NotNull(message = "Vehicle first rolling date plate can't be null")
        LocalDate firstRollingDate,
        List<Long> drivers
) {
}
