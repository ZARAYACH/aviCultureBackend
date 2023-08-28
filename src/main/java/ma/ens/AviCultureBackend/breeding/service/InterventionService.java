package ma.ens.AviCultureBackend.breeding.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.InterventionMapper;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.Intervention;
import ma.ens.AviCultureBackend.breeding.modal.dto.InterventionDto;
import ma.ens.AviCultureBackend.breeding.repository.InterventionRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterventionService {

    private final InterventionRepo interventionRepo;
    private final InterventionMapper interventionMapper;
    private final BuildingService buildingService;

    public List<Intervention> getInterventionsByBuilding(Building building) {
        Assert.notNull(building, "provided building is null");
        return interventionRepo.findAllByBuilding(building);
    }

    public Intervention getInterventionByIdAndBuilding(Long id, Building building) throws NotFoundException {
        Assert.notNull(building, "provided building is null");
        return interventionRepo.findByIdAndBuilding(id, building)
                .orElseThrow(() -> new NotFoundException("Intervention with id " + id + " and with building id " + building.getId() + " not found"));
    }

    public Intervention addInterventionWithBuilding(Building building, InterventionDto interventionDto) {
        Assert.notNull(interventionDto, "Provided intervention dto can't be null");
        Assert.notNull(building, "Provided Building can't be null");
        return interventionRepo.save(Intervention.builder()
                .nature(interventionDto.nature())
                .beginningDate(interventionDto.beginningDate())
                .endDate(interventionDto.endDate())
                .building(building)
                .build());
    }

    public Intervention modifyIntervention(Intervention intervention, InterventionDto interventionDto) {
        Assert.notNull(intervention, "Provided intervention can't be null");
        Assert.notNull(interventionDto, "Provided intervention dto can't be null");
        intervention.setNature(interventionDto.nature());
        intervention.setBeginningDate(interventionDto.beginningDate());
        intervention.setEndDate(interventionDto.endDate());
        return interventionRepo.save(intervention);

    }

    public void deleteInterventionByIdAndBuilding(Long id, Building building) throws NotFoundException {
        Assert.notNull(building, "Provided Building can't be null");
        Intervention intervention = interventionRepo.findByIdAndBuilding(id, building)
                .orElseThrow(() -> new NotFoundException("Intervention with id " + id + " and with building id " + building.getId() + " not found"));
        interventionRepo.delete(intervention);
    }
}
