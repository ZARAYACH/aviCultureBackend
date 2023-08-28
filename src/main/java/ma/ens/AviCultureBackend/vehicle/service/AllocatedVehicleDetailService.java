package ma.ens.AviCultureBackend.vehicle.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.service.BreedingCenterService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.vehicle.modal.AllocatedVehicleDetail;
import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import ma.ens.AviCultureBackend.vehicle.modal.dto.AllocatedVehicleDetailDto;
import ma.ens.AviCultureBackend.vehicle.repository.AllocatedVehicleDetailRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocatedVehicleDetailService {
    private final AllocatedVehicleDetailRepo allocatedVehicleDetailRepo;
    private final VehicleService vehicleService;
    private final BreedingCenterService breedingCenterService;

    public List<AllocatedVehicleDetail> getAllAllocatedVehicleDetails() {
        return allocatedVehicleDetailRepo.findAll();
    }

    public AllocatedVehicleDetail getAllocatedVehicleDetailById(Long id) throws NotFoundException {
        return allocatedVehicleDetailRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("AllocatedVehicleDetail with id " + id + " not found"));
    }

    public List<AllocatedVehicleDetail> getAllAllocatedVehicleDetailsByBreadingCenter(BreedingCenter breedingCenter) {
        return allocatedVehicleDetailRepo.findAllByVehicle(breedingCenter);
    }

    public List<AllocatedVehicleDetail> getAllAllocatedVehicleDetailsByVehicle(Vehicle vehicle) {
        return allocatedVehicleDetailRepo.findAllByVehicle(vehicle);
    }


    public AllocatedVehicleDetail getAllAllocatedVehicleDetailsByIdAndByBreadingCenter(Long id, BreedingCenter center) throws NotFoundException {
        return allocatedVehicleDetailRepo.findByIdAndBreedingCenter(id, center)
                .orElseThrow(() -> new NotFoundException("AllocatedVehicleDetail with id " + id + " and breeding Center id " + center.getId() + " not found"));
    }

    public AllocatedVehicleDetail addAllocatedVehicleDetailWithBreedingCenter(AllocatedVehicleDetailDto allocatedVehicleDetailDto, BreedingCenter breedingCenter) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(allocatedVehicleDetailDto, "AllocatedVehicleDetail dto provided is null");
        Assert.notNull(breedingCenter, "BreedingCenter provided is null");
        Vehicle vehicle = vehicleService.getVehicleById(allocatedVehicleDetailDto.vehicleId());
        return allocatedVehicleDetailRepo.save(AllocatedVehicleDetail.builder()
                .allocationDate(allocatedVehicleDetailDto.allocationDate())
                .deAllocationDate(allocatedVehicleDetailDto.deAllocationDate())
                .breedingCenter(breedingCenter)
                .vehicle(vehicle)
                .reason(allocatedVehicleDetailDto.reason())
                .build());
    }

    public AllocatedVehicleDetail modifyAllocatedVehicleDetail(AllocatedVehicleDetail allocatedVehicleDetail, AllocatedVehicleDetailDto allocatedVehicleDetailDto) throws NotFoundException {
        Assert.notNull(allocatedVehicleDetailDto, "AllocatedVehicleDetail dto provided is null");
        Assert.notNull(allocatedVehicleDetail, "AllocatedVehicleDetail provided is null");
        Vehicle vehicle = vehicleService.getVehicleById(allocatedVehicleDetailDto.vehicleId());
        allocatedVehicleDetail.setVehicle(vehicle);
        allocatedVehicleDetail.setReason(allocatedVehicleDetailDto.reason());
        allocatedVehicleDetail.setAllocationDate(allocatedVehicleDetailDto.allocationDate());
        allocatedVehicleDetail.setDeAllocationDate(allocatedVehicleDetailDto.deAllocationDate());
        allocatedVehicleDetail.setVehicle(vehicle);
        return allocatedVehicleDetailRepo.save(allocatedVehicleDetail);
    }

    public void deleteAllocatedVehicleDetail(AllocatedVehicleDetail allocatedVehicleDetail) throws IllegalArgumentException {
        allocatedVehicleDetailRepo.delete(allocatedVehicleDetail);
    }
}
