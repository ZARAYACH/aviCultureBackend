package ma.ens.AviCultureBackend.task.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.task.mapper.TaskMapper;
import ma.ens.AviCultureBackend.task.modal.MedicationTask;
import ma.ens.AviCultureBackend.task.modal.dto.MedicationTaskDto;
import ma.ens.AviCultureBackend.task.service.MedicationTaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medication-tasks")
@RequiredArgsConstructor
public class MedicationTaskController {

    private final MedicationTaskService medicationTaskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<MedicationTaskDto> getMedicationTaskServices() {
        return taskMapper.toMedicationTaskDtos(medicationTaskService.getMedicationTask());
    }

    @GetMapping("/{medicationTaskId}")
    public MedicationTaskDto getMedicationTaskById(@PathVariable(name = "medicationTaskId") Long id) throws NotFoundException {
        return taskMapper.toMedicationTaskDto(medicationTaskService
                .getMedicationTaskById(id));
    }

    @PostMapping("/add")
    public MedicationTaskDto addMedicationTask(@Validated @RequestBody MedicationTaskDto medicationTaskDto) throws BadRequestExeption, NotFoundException {
        try {
            return taskMapper.toMedicationTaskDto(
                    medicationTaskService.addMedicationTask(medicationTaskDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{medicationTaskId}/modify")
    public MedicationTaskDto ModifyMedicationTask(@PathVariable(name = "medicationTaskId") Long id,
                                                              @Validated @RequestBody MedicationTaskDto medicationTaskDto) throws BadRequestExeption, NotFoundException {
        try {
            MedicationTask MedicationTask = medicationTaskService.getMedicationTaskById(id);
            return taskMapper.toMedicationTaskDto(medicationTaskService
                    .modifyMedicationTask(MedicationTask, medicationTaskDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{medicationTaskId}/delete")
    public void deleteMedicationTask(@PathVariable(name = "medicationTaskId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            medicationTaskService.deleteMedicationTask(medicationTaskService.getMedicationTaskById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
