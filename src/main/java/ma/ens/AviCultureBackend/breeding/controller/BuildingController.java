package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BuildingMapper;
import ma.ens.AviCultureBackend.breeding.model.Building;
import ma.ens.AviCultureBackend.breeding.model.BuildingDto;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-centers")
@RestController
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingMapper buildingMapper;

    @GetMapping
    public List<BuildingDto> getAllBuildings() {
        return buildingMapper.toBuildingDtos(buildingService.getAllBuildings());
    }

    @PostMapping("/add")
    public BuildingDto ModifyBuilding(@RequestBody BuildingDto BuildingDto) throws BadRequestExeption {
        try {
            return buildingMapper.toBuildingDto(buildingService
                    .addBuilding(buildingMapper.toBuilding(BuildingDto)));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{BuildingId}/modify")
    public BuildingDto ModifyBuilding(@PathVariable(name = "BuildingId") Long id,
                                      @RequestBody BuildingDto BuildingDto) throws BadRequestExeption, NotFoundException {
        try {
            Building Building = buildingService.getBuildingById(id);
            return buildingMapper.toBuildingDto(buildingService
                    .modifyBuilding(Building, BuildingDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{BuildingId}/delete")
    public void ModifyBuilding(@PathVariable(name = "BuildingId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            buildingService.deleteBuilding(buildingService.getBuildingById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
