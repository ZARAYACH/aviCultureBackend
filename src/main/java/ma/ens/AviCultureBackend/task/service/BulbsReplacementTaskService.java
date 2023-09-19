package ma.ens.AviCultureBackend.task.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.task.modal.BulbsReplacementTask;
import ma.ens.AviCultureBackend.task.modal.dto.BulbsReplacementTaskDto;
import ma.ens.AviCultureBackend.task.repo.BulbsReplacementTaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulbsReplacementTaskService {
    private final BulbsReplacementTaskRepo bulbsReplacementTaskRepo;
    private final BuildingService buildingService;

    public List<BulbsReplacementTask> getBulbsReplacementTasks() {
        return bulbsReplacementTaskRepo.findAll();
    }

    public BulbsReplacementTask getBulbsReplacementTaskById(Long id) throws NotFoundException {
        return bulbsReplacementTaskRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("BulbsReplacementTask with id " + id + " not found"));
    }

    public BulbsReplacementTask addBulbsReplacementTask(BulbsReplacementTaskDto bulbsReplacementTaskDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(bulbsReplacementTaskDto, "BulbsReplacementTaskDto provided is null");
        Building building = buildingService.getBuildingById(bulbsReplacementTaskDto.building().id());
        return bulbsReplacementTaskRepo.save(BulbsReplacementTask.builder()
                .building(building)
                .replacedBulbCount(bulbsReplacementTaskDto.replacedBulbCount())
                .date(bulbsReplacementTaskDto.date())
                .build());
    }

    public BulbsReplacementTask modifyBulbsReplacementTask(BulbsReplacementTask bulbsReplacementTask, BulbsReplacementTaskDto bulbsReplacementTaskDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(bulbsReplacementTask, "BulbsReplacementTask provided is null");
        Assert.notNull(bulbsReplacementTaskDto, "bulbsReplacementTaskDto provided is null");
        Building building = buildingService.getBuildingById(bulbsReplacementTaskDto.building().id());
        bulbsReplacementTask.setReplacedBulbCount(bulbsReplacementTask.getReplacedBulbCount());
        bulbsReplacementTask.setBuilding(building);
        bulbsReplacementTask.setDate(bulbsReplacementTaskDto.date());
        return bulbsReplacementTaskRepo.save(bulbsReplacementTask);
    }

    public void deleteBulbsReplacementTask(BulbsReplacementTask bulbsReplacementTask) throws IllegalArgumentException {
        bulbsReplacementTaskRepo.delete(bulbsReplacementTask);
    }
}
