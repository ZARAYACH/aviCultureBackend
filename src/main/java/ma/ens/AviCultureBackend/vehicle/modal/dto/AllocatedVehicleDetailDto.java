package ma.ens.AviCultureBackend.vehicle.modal.dto;

import java.time.LocalDateTime;

public record AllocatedVehicleDetailDto(
        Long id,
        String reason,
        Long vehicleId,
        Long breedingCenterId,
        LocalDateTime assignedDate,
        LocalDateTime allocationDate,
        LocalDateTime deAllocationDate
) {
}
