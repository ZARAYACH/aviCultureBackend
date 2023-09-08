package ma.ens.AviCultureBackend.breeding.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BuildingMapper;
import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;
import ma.ens.AviCultureBackend.breeding.repository.BuildingRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepo buildingRepo;
    private final BuildingMapper buildingMapper;

    private final BreedingCenterService breedingCenterService;

    public List<Building> getAllBuildings() {
        return buildingRepo.findAll();
    }

    public Building getBuildingById(Long id) throws NotFoundException {
        return buildingRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding Building with id " + id + " not found"));
    }


    public Building getStorageBuildingById(Long id) throws NotFoundException {
        return buildingRepo.findStorageBuildingById(id)
                .orElseThrow(() -> new NotFoundException("Strorage building with id " + id + " not found"));
    }

    public Building addBuilding(BuildingDto buildingDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(buildingDto, "buildingDto provided is null");
        BreedingCenter breedingCenter = breedingCenterService.getBreedingCenterById(buildingDto.breedingCenter().id());
        return buildingRepo.save(Building.builder()
                .name(buildingDto.name())
                .state(buildingDto.state() != null ? buildingDto.state() : Building.BreedingBuildingState.FREE)
                .surface(buildingDto.surface())
                .humidityRate(buildingDto.humidityRate())
                .breedingCenter(breedingCenter)
                .temperature(buildingDto.temperature())
                .nature(buildingDto.nature())
                .build());
    }

    public Building modifyBuilding(Building building, BuildingDto buildingDto) throws NotFoundException {
        Assert.notNull(buildingDto, "breeding center provided is null");
        building.setState(buildingDto.state());
        building.setBreedingCenter(breedingCenterService.getBreedingCenterById(buildingDto.breedingCenter().id()));
        building.setName(buildingDto.name());
        building.setSurface(buildingDto.surface());
        building.setTemperature(buildingDto.temperature());
        building.setHumidityRate(buildingDto.humidityRate());
        return buildingRepo.save(building);
    }

    public void deleteBuilding(Building building) throws IllegalArgumentException {
        buildingRepo.delete(building);
    }

    public List<Building> getBuildingByIds(List<Long> ids) {
        return buildingRepo.findAllById(ids);
    }

    public List<Building> getAllBuildingsByNature(Building.Nature buildingNature) {
        return buildingRepo.findAllByNature(buildingNature);
    }
}
