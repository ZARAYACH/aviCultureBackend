package ma.ens.AviCultureBackend.vehicle.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.user.service.UserService;
import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import ma.ens.AviCultureBackend.vehicle.modal.dto.VehicleDto;
import ma.ens.AviCultureBackend.vehicle.repository.VehicleRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepo vehicleRepo;
    private final UserService userService;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    public Vehicle getVehicleById(Long id) throws NotFoundException {
        return vehicleRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding Vehicle with id " + id + " not found"));
    }

    public Vehicle addVehicle(VehicleDto vehicleDto) throws IllegalArgumentException {
        Assert.notNull(vehicleDto, "Vehicle dto provided is null");
        Vehicle.VehicleBuilder vehicleBuilder = Vehicle.builder()
                .licencePlate(vehicleDto.licencePlate())
                .marque(vehicleDto.marque())
                .type(vehicleDto.type())
                .firstRollingDate(vehicleDto.firstRollingDate())
                .module(vehicleDto.module());
        if (vehicleDto.drivers() != null && !vehicleDto.drivers().isEmpty()) {
            vehicleBuilder.drivers(userService.getUserDriversByIds(vehicleDto.drivers()));
        }
        return vehicleRepo.save(vehicleBuilder.build());
    }

    public Vehicle modifyVehicle(Vehicle vehicle, VehicleDto vehicleDto) throws NotFoundException {
        Assert.notNull(vehicleDto, "Vehicle dto provided is null");
        Assert.notNull(vehicle, "Vehicle provided is null");
        vehicle.setMarque(vehicleDto.marque());
        vehicle.setType(vehicle.getType());
        vehicle.setModule(vehicleDto.module());
        vehicle.setMarque(vehicleDto.marque());
        vehicle.setLicencePlate(vehicle.getLicencePlate());
        vehicle.setFirstRollingDate(vehicleDto.firstRollingDate());
        if (vehicleDto.drivers() != null && !vehicleDto.drivers().isEmpty()) {
            vehicle.setDrivers(userService.getUserDriversByIds(vehicleDto.drivers()));
        } else {
            vehicle.setDrivers(new ArrayList<>());
        }
        return vehicleRepo.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) throws IllegalArgumentException {
        vehicleRepo.delete(vehicle);
    }

}
