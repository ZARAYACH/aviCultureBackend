package ma.ens.AviCultureBackend.vehicle.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.vehicle.mapper.VehicleMapper;
import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import ma.ens.AviCultureBackend.vehicle.modal.VehicleIntervention;
import ma.ens.AviCultureBackend.vehicle.modal.dto.VehicleInterventionDto;
import ma.ens.AviCultureBackend.vehicle.service.VehicleInterventionService;
import ma.ens.AviCultureBackend.vehicle.service.VehicleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles/{vehicleId}/interventions")
@RequiredArgsConstructor
public class VehicleInterventionController {

    private final VehicleService vehicleService;
    private final VehicleInterventionService vehicleInterventionService;
    private final VehicleMapper vehicleMapper;

    @GetMapping
    public List<VehicleInterventionDto> getAllVehicleIntervention(@PathVariable(name = "vehicleId") Long vehicleId) throws NotFoundException {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return vehicleMapper.toVehicleInterventionDtos(vehicleInterventionService.getAllVehicleInterventions(vehicle));
    }

    @PostMapping("/add")
    public VehicleInterventionDto addInterventionVehicle(@PathVariable(name = "vehicleId") Long vehicleId,
                                                         @Validated @RequestBody VehicleInterventionDto vehicleDto) throws BadRequestExeption, NotFoundException {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            return vehicleMapper.toVehicleInterventionDto(
                    vehicleInterventionService.addVehicleInterventionWithVehicle(vehicleDto, vehicle));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @GetMapping("/{interventionId}")
    public VehicleInterventionDto getInterventionVehicleById(@PathVariable(name = "vehicleId") Long vehicleId,
                                                             @PathVariable(name = "interventionId") Long interventionId) throws NotFoundException {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return vehicleMapper.toVehicleInterventionDto(vehicleInterventionService
                .getVehicleInterventionByIdAndVehicle(interventionId, vehicle));
    }

    @PutMapping("/{interventionId}/modify")
    public VehicleInterventionDto ModifyInterventionVehicle(@PathVariable("interventionId") Long interventionId,
                                                            @PathVariable(name = "vehicleId") Long vehicleId,
                                                            @Validated @RequestBody VehicleInterventionDto vehicleInterventionDto) throws BadRequestExeption, NotFoundException {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            VehicleIntervention vehicleIntervention = vehicleInterventionService.getVehicleInterventionByIdAndVehicle(interventionId, vehicle);
            return vehicleMapper.toVehicleInterventionDto(vehicleInterventionService.
                    modifyVehicleIntervention(vehicleIntervention, vehicleInterventionDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{interventionId}/delete")
    public void deleteVehicleIntervention(@PathVariable(name = "vehicleId") Long vehicleId,
                                          @PathVariable(name = "interventionId") Long interventionId) throws BadRequestExeption, NotFoundException {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            VehicleIntervention vehicleIntervention = vehicleInterventionService.getVehicleInterventionByIdAndVehicle(interventionId, vehicle);
            vehicleInterventionService.deleteVehicleIntervention(vehicleIntervention);
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
