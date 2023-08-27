package ma.ens.AviCultureBackend.task.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.task.mapper.TaskMapper;
import ma.ens.AviCultureBackend.task.modal.BulbsReplacementTask;
import ma.ens.AviCultureBackend.task.modal.dto.BulbsReplacementTaskDto;
import ma.ens.AviCultureBackend.task.service.BulbsReplacementTaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bulbs-replacement-tasks")
@RequiredArgsConstructor
public class BulbsReplacementTaskController {

    private final BulbsReplacementTaskService bulbsReplacementTaskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<BulbsReplacementTaskDto> getBulbsReplacementTaskServices() {
        return taskMapper.toBulbsReplacementTaskDtos(bulbsReplacementTaskService.getBulbsReplacementTasks());
    }

    @GetMapping("/{bulbsReplacementTaskId}")
    public BulbsReplacementTaskDto getBulbsReplacementTaskById(@PathVariable(name = "bulbsReplacementTaskId") Long id) throws NotFoundException {
        return taskMapper.toBulbsReplacementTaskDto(bulbsReplacementTaskService
                .getBulbsReplacementTaskById(id));
    }

    @PostMapping("/add")
    public BulbsReplacementTaskDto addBulbsReplacementTask(@Validated @RequestBody BulbsReplacementTaskDto bulbsReplacementTaskDto) throws BadRequestExeption, NotFoundException {
        try {
            return taskMapper.toBulbsReplacementTaskDto(
                    bulbsReplacementTaskService.addBulbsReplacementTask(bulbsReplacementTaskDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{BulbsReplacementTaskId}/modify")
    public BulbsReplacementTaskDto ModifyBulbsReplacementTask(@PathVariable(name = "BulbsReplacementTaskId") Long id,
                                                              @Validated @RequestBody BulbsReplacementTaskDto bulbsReplacementTaskDto) throws BadRequestExeption, NotFoundException {
        try {
            BulbsReplacementTask BulbsReplacementTask = bulbsReplacementTaskService.getBulbsReplacementTaskById(id);
            return taskMapper.toBulbsReplacementTaskDto(bulbsReplacementTaskService
                    .modifyBulbsReplacementTask(BulbsReplacementTask, bulbsReplacementTaskDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{BulbsReplacementTaskId}/delete")
    public void deleteBulbsReplacementTask(@PathVariable(name = "BulbsReplacementTaskId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            bulbsReplacementTaskService.deleteBulbsReplacementTask(bulbsReplacementTaskService.getBulbsReplacementTaskById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
