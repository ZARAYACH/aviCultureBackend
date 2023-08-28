package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.InterventionMapper;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.Intervention;
import ma.ens.AviCultureBackend.breeding.modal.dto.InterventionDto;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.breeding.service.InterventionService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-buildings/{buildingId}/interventions")
@RestController
@RequiredArgsConstructor
public class InterventionController {

    private final InterventionMapper interventionMapper;
    private final InterventionService interventionService;
    private final BuildingService buildingService;

    @GetMapping
    public List<InterventionDto> getAllInterventionsByBuildingId(@PathVariable(name = "buildingId") Long id) throws NotFoundException {
        Building building = buildingService.getBuildingById(id);
        return interventionMapper.toInterventionDtos(interventionService.getInterventionsByBuilding(building));
    }

    @PostMapping("/add")
    public InterventionDto addBreedingBlooks(@PathVariable(name = "buildingId") Long id,
                                             @Validated @RequestBody InterventionDto interventionDto) throws BadRequestExeption, NotFoundException {
        try {
            Building building = buildingService.getBuildingById(id);
            return interventionMapper.toInterventionDto(interventionService
                    .addInterventionWithBuilding(building, interventionDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{id}/modify")
    public InterventionDto ModifyBreedingBlocks(@PathVariable(name = "buildingId") Long buildingId,
                                                @PathVariable(name = "id") Long interventionId,
                                                @Validated @RequestBody InterventionDto interventionDto) throws BadRequestExeption, NotFoundException {
        Building building = buildingService.getBuildingById(buildingId);
        try {
            Intervention intervention = interventionService.getInterventionByIdAndBuilding(interventionId, building);
            return interventionMapper.toInterventionDto(interventionService
                    .modifyIntervention(intervention, interventionDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public void deleteBreedingBlock(@PathVariable(name = "buildingId") Long buildingId,
                                    @PathVariable(name = "id") Long interventionId) throws BadRequestExeption, NotFoundException {
        Building building = buildingService.getBuildingById(buildingId);
        try {
            interventionService.deleteInterventionByIdAndBuilding(interventionId, building);
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
