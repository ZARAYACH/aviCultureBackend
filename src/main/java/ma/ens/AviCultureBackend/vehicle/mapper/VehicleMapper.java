package ma.ens.AviCultureBackend.vehicle.mapper;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.vehicle.model.Vehicle;
import ma.ens.AviCultureBackend.vehicle.model.VehicleDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VehicleMapper {
    List<VehicleDto> toVehicleDtos(List<Vehicle> allVehicles);

    VehicleDto toVehicleDto(Vehicle vehicle);

    default Long mapDriver(User user) {
        if (user != null) {
            return user.getId();
        }
        return null;
    }
}
