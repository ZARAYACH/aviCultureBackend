package ma.ens.AviCultureBackend.breeding.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.model.Building;
import ma.ens.AviCultureBackend.breeding.model.BuildingDto;
import ma.ens.AviCultureBackend.breeding.repository.BuildingRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepo buildingRepo;

    public List<Building> getAllBuildings() {
        return buildingRepo.findAll();
    }

    public Building getBuildingById(Long id) throws NotFoundException {
        return buildingRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding Building with id " + id + " not found"));
    }

    public Building addBuilding(Building Building) throws IllegalArgumentException {
        Assert.notNull(Building, "building provided is null");
        return buildingRepo.save(Building);
    }

    public Building modifyBuilding(Building building, BuildingDto buildingDto) {
        Assert.notNull(buildingDto, "breeding center provided is null");
        building.setState(buildingDto.state());
//      building.setBlocks(buildingDto.blocks());  TODO://figure out later
        building.setName(buildingDto.name());
        building.setSurface(buildingDto.surface());
        building.setTemperature(buildingDto.temperature());
        building.setHumidityRate(buildingDto.humidityRate());
        return buildingRepo.save(building);
    }

    public void deleteBuilding(Building building) throws IllegalArgumentException {
        buildingRepo.delete(building);
    }
}
