package ma.ens.AviCultureBackend.breeding.controller;


import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.service.BreedingCenterService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.vehicle.mapper.VehicleMapper;
import ma.ens.AviCultureBackend.vehicle.modal.AllocatedVehicleDetail;
import ma.ens.AviCultureBackend.vehicle.modal.dto.AllocatedVehicleDetailDto;
import ma.ens.AviCultureBackend.vehicle.service.AllocatedVehicleDetailService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-centers/{centerId}/allocated-vehicles")
@RestController
@RequiredArgsConstructor
public class BreedingCenterAllocatedVehicleController {

    public final BreedingCenterService breedingCenterService;
    public final AllocatedVehicleDetailService allocatedVehicleDetailService;
    public final VehicleMapper vehicleMapper;

    @GetMapping
    public List<AllocatedVehicleDetailDto> getAllAllocatedVehicleDetails(@PathVariable(name = "centerId") Long centerId) throws NotFoundException {
        BreedingCenter center = breedingCenterService.getBreedingCenterById(centerId);
        return vehicleMapper.toAllocatedVehicleDetailsDtos(allocatedVehicleDetailService
                .getAllAllocatedVehicleDetailsByBreadingCenter(center));
    }

    @PostMapping("/add")
    public AllocatedVehicleDetailDto addAllocatedVehicleDetails(@PathVariable(name = "centerId") Long centerId,
                                                                @Validated @RequestBody AllocatedVehicleDetailDto vehicleDto) throws BadRequestExeption, NotFoundException {
        try {
            BreedingCenter center = breedingCenterService.getBreedingCenterById(centerId);
            return vehicleMapper.toAllocatedVehicleDetailsDto(
                    allocatedVehicleDetailService.addAllocatedVehicleDetailWithBreedingCenter(vehicleDto, center));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AllocatedVehicleDetailDto getAllocatedVehicleDetailById(@PathVariable(name = "centerId") Long centerId,
                                                                   @PathVariable(name = "id") Long id) throws NotFoundException {
        BreedingCenter center = breedingCenterService.getBreedingCenterById(centerId);
        return vehicleMapper.toAllocatedVehicleDetailsDto(allocatedVehicleDetailService
                .getAllAllocatedVehicleDetailsByIdAndByBreadingCenter(id, center));
    }

    @PutMapping("/{id}/modify")
    public AllocatedVehicleDetailDto ModifyInterventionVehicle(@PathVariable("id") Long id,
                                                               @PathVariable(name = "centerId") Long centerId,
                                                               @Validated @RequestBody AllocatedVehicleDetailDto allocatedVehicleDetailDto) throws BadRequestExeption, NotFoundException {
        try {
            BreedingCenter breedingCenter = breedingCenterService.getBreedingCenterById(centerId);
            AllocatedVehicleDetail allocatedVehicleDetail = allocatedVehicleDetailService
                    .getAllAllocatedVehicleDetailsByIdAndByBreadingCenter(id, breedingCenter);
            return vehicleMapper.toAllocatedVehicleDetailsDto(allocatedVehicleDetailService
                    .modifyAllocatedVehicleDetail(allocatedVehicleDetail, allocatedVehicleDetailDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public void deleteVehicleIntervention(@PathVariable(name = "id") Long id,
                                          @PathVariable(name = "centerId") Long centerId) throws BadRequestExeption, NotFoundException {
        try {
            BreedingCenter center = breedingCenterService.getBreedingCenterById(centerId);
            AllocatedVehicleDetail allocatedVehicleDetail = allocatedVehicleDetailService
                    .getAllAllocatedVehicleDetailsByIdAndByBreadingCenter(id, center);
            allocatedVehicleDetailService.deleteAllocatedVehicleDetail(allocatedVehicleDetail);
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
