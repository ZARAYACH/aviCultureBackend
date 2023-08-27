package ma.ens.AviCultureBackend.task.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.task.mapper.TaskMapper;
import ma.ens.AviCultureBackend.task.modal.VaccinationTask;
import ma.ens.AviCultureBackend.task.modal.dto.VaccinationTaskDto;
import ma.ens.AviCultureBackend.task.service.VaccinationTaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vaccination-tasks")
@RequiredArgsConstructor
public class VaccinationTaskController {

    private final VaccinationTaskService vaccinationTaskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<VaccinationTaskDto> getVaccinationTaskServices() {
        return taskMapper.toVaccinationTaskDtos(vaccinationTaskService.getVaccinationTask());
    }

    @GetMapping("/{vaccinationTaskId}")
    public VaccinationTaskDto getVaccinationTaskById(@PathVariable(name = "vaccinationTaskId") Long id) throws NotFoundException {
        return taskMapper.toVaccinationTaskDto(vaccinationTaskService
                .getVaccinationTaskById(id));
    }

    @PostMapping("/add")
    public VaccinationTaskDto addVaccinationTask(@Validated @RequestBody VaccinationTaskDto vaccinationTaskDto) throws BadRequestExeption, NotFoundException {
        try {
            return taskMapper.toVaccinationTaskDto(
                    vaccinationTaskService.addVaccinationTask(vaccinationTaskDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{vaccinationTaskId}/modify")
    public VaccinationTaskDto ModifyVaccinationTask(@PathVariable(name = "vaccinationTaskId") Long id,
                                                              @Validated @RequestBody VaccinationTaskDto vaccinationTaskDto) throws BadRequestExeption, NotFoundException {
        try {
            VaccinationTask VaccinationTask = vaccinationTaskService.getVaccinationTaskById(id);
            return taskMapper.toVaccinationTaskDto(vaccinationTaskService
                    .modifyVaccinationTask(VaccinationTask, vaccinationTaskDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{vaccinationTaskId}/delete")
    public void deleteVaccinationTask(@PathVariable(name = "vaccinationTaskId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            vaccinationTaskService.deleteVaccinationTask(vaccinationTaskService.getVaccinationTaskById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
