package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BuildingMapper;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.dto.BuildingDto;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-buildings")
@RestController
@RequiredArgsConstructor
@Secured(UserRole.Role.ROLE_MANAGER_VALUE)
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingMapper buildingMapper;

    @GetMapping
    public List<BuildingDto> getAllBuildings() {
        return buildingMapper.toBuildingDtos(buildingService.getAllBuildings());
    }

    @GetMapping("/{buildingId}")
    public BuildingDto getBuildingById(@PathVariable(name = "buildingId") Long buildingId) throws NotFoundException {
        return buildingMapper.toBuildingDto(buildingService.getBuildingById(buildingId));
    }

    @PostMapping("/add")
    public BuildingDto addBuilding(@Validated @RequestBody BuildingDto BuildingDto) throws BadRequestExeption, NotFoundException {
        try {
            return buildingMapper.toBuildingDto(buildingService
                    .addBuilding(BuildingDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{buildingId}/modify")
    public BuildingDto ModifyBuilding(@PathVariable(name = "buildingId") Long id,
                                      @Validated @RequestBody BuildingDto buildingDto) throws BadRequestExeption, NotFoundException {
        try {
            Building Building = buildingService.getBuildingById(id);
            return buildingMapper.toBuildingDto(buildingService
                    .modifyBuilding(Building, buildingDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{buildingId}/delete")
    public void deleteBuilding(@PathVariable(name = "buildingId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            buildingService.deleteBuilding(buildingService.getBuildingById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
