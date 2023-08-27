package ma.ens.AviCultureBackend.vehicle.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.vehicle.mapper.VehicleMapper;
import ma.ens.AviCultureBackend.vehicle.model.Vehicle;
import ma.ens.AviCultureBackend.vehicle.model.VehicleDto;
import ma.ens.AviCultureBackend.vehicle.service.VehicleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    @GetMapping
    public List<VehicleDto> getAllVehicles() {
        return vehicleMapper.toVehicleDtos(vehicleService.getAllVehicles());
    }

    @PostMapping("/add")
    public VehicleDto addVehicle(@Validated @RequestBody VehicleDto vehicleDto) throws BadRequestExeption {
        try {
            return vehicleMapper.toVehicleDto(
                    vehicleService.addVehicle(vehicleDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @GetMapping("/{vehicleId}")
    public VehicleDto getVehicleById(@PathVariable(name = "vehicleId") Long id) throws NotFoundException {
        return vehicleMapper.toVehicleDto(vehicleService
                .getVehicleById(id));
    }

    @PutMapping("/{vehicleId}/modify")
    public VehicleDto ModifyVehicle(@PathVariable(name = "vehicleId") Long id,
                                    @Validated @RequestBody VehicleDto vehicleDto) throws BadRequestExeption, NotFoundException {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            return vehicleMapper.toVehicleDto(vehicleService
                    .modifyVehicle(vehicle, vehicleDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{vehicleId}/delete")
    public void deleteVehicle(@PathVariable(name = "vehicleId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            vehicleService.deleteVehicle(vehicleService.getVehicleById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
