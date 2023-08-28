package ma.ens.AviCultureBackend.vehicle.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import ma.ens.AviCultureBackend.vehicle.modal.VehicleIntervention;
import ma.ens.AviCultureBackend.vehicle.modal.dto.VehicleInterventionDto;
import ma.ens.AviCultureBackend.vehicle.repository.VehicleInterventionRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleInterventionService {

    private final VehicleInterventionRepo vehicleInterventionRepo;
    public VehicleIntervention getVehicleInterventionByIdAndVehicle(Long interventionId, Vehicle vehicle) throws NotFoundException {
        return vehicleInterventionRepo.findByIdAndVehicle(interventionId, vehicle)
                .orElseThrow(() -> new NotFoundException("Vehicle Intervention with id " + interventionId + " and vehicle id " + vehicle.getId() + " not found"));
    }

    public List<VehicleIntervention> getAllVehicleInterventions(Vehicle vehicle) {
        return vehicleInterventionRepo.findAllByVehicle(vehicle);
    }

    public VehicleIntervention getVehicleInterventionById(Long id) throws NotFoundException {
        return vehicleInterventionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("VehicleIntervention with id " + id + " not found"));
    }

    public VehicleIntervention addVehicleInterventionWithVehicle(VehicleInterventionDto vehicleInterventionDto, Vehicle vehicle) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(vehicleInterventionDto, "VehicleIntervention dto provided is null");
        return vehicleInterventionRepo.save(VehicleIntervention.builder()
                .date(vehicleInterventionDto.date())
                .description(vehicleInterventionDto.description())
                .price(vehicleInterventionDto.price())
                .mechanicName(vehicleInterventionDto.mechanicName())
                .kilometerage(vehicleInterventionDto.kilometerage())
                .nature(vehicleInterventionDto.nature())
                .vehicle(vehicle).build());
    }

    public VehicleIntervention modifyVehicleIntervention(VehicleIntervention vehicleIntervention, VehicleInterventionDto vehicleInterventionDto) throws NotFoundException {
        Assert.notNull(vehicleInterventionDto, "VehicleIntervention dto provided is null");
        Assert.notNull(vehicleIntervention, "VehicleIntervention provided is null");
        vehicleIntervention.setDescription(vehicleInterventionDto.description());
        vehicleIntervention.setPrice(vehicleInterventionDto.price());
        vehicleIntervention.setMechanicName(vehicleInterventionDto.mechanicName());
        vehicleIntervention.setKilometerage(vehicleIntervention.getKilometerage());
        vehicleIntervention.setNature(vehicleInterventionDto.nature());
        vehicleIntervention.setDate(vehicleInterventionDto.date());
        return vehicleInterventionRepo.save(vehicleIntervention);
    }

    public void deleteVehicleIntervention(VehicleIntervention vehicleIntervention) throws IllegalArgumentException {
        vehicleInterventionRepo.delete(vehicleIntervention);
    }
}
