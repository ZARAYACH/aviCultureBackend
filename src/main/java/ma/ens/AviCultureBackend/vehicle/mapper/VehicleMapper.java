package ma.ens.AviCultureBackend.vehicle.mapper;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.vehicle.modal.AllocatedVehicleDetail;
import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import ma.ens.AviCultureBackend.vehicle.modal.VehicleIntervention;
import ma.ens.AviCultureBackend.vehicle.modal.dto.AllocatedVehicleDetailDto;
import ma.ens.AviCultureBackend.vehicle.modal.dto.VehicleDto;
import ma.ens.AviCultureBackend.vehicle.modal.dto.VehicleInterventionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VehicleMapper {
    List<VehicleDto> toVehicleDtos(List<Vehicle> allVehicles);

    VehicleDto toVehicleDto(Vehicle vehicle);

    List<VehicleInterventionDto> toVehicleInterventionDtos(List<VehicleIntervention> vehicleInterventions);
    @Mapping(source = "vehicle.id", target = "vehicleId")
    VehicleInterventionDto toVehicleInterventionDto(VehicleIntervention vehicleIntervention);

    List<AllocatedVehicleDetailDto> toAllocatedVehicleDetailsDtos(List<AllocatedVehicleDetail> allocatedVehicleDetails);

    AllocatedVehicleDetailDto toAllocatedVehicleDetailsDto(AllocatedVehicleDetail allocatedVehicleDetail);

    default Long mapDriver(User user) {
        if (user != null) {
            return user.getId();
        }
        return null;
    }
}
