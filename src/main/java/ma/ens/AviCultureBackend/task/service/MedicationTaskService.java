package ma.ens.AviCultureBackend.task.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.model.Block;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.service.DiseaseService;
import ma.ens.AviCultureBackend.task.modal.MedicationTask;
import ma.ens.AviCultureBackend.task.modal.dto.MedicationTaskDto;
import ma.ens.AviCultureBackend.task.repo.MedicationTaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationTaskService {
    private final MedicationTaskRepo medicationTaskRepo;
    private final BlockService blockService;
    private final DiseaseService diseaseService;

    public List<MedicationTask> getMedicationTask() {
        return medicationTaskRepo.findAll();
    }

    public MedicationTask getMedicationTaskById(Long id) throws NotFoundException {
        return medicationTaskRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("medication Task with id " + id + " not found"));
    }

    public MedicationTask addMedicationTask(MedicationTaskDto medicationTaskDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(medicationTaskDto, "MedicationTaskDto provided is null");
        Block block = blockService.getBlockById(medicationTaskDto.blockId());
        Disease disease = diseaseService.getDiseaseById(medicationTaskDto.diseaseId());
        return medicationTaskRepo.save(MedicationTask.builder()
                .block(block)
                .disease(disease)
                .date(medicationTaskDto.date())
                .build());
    }

    public MedicationTask modifyMedicationTask(MedicationTask medicationTask, MedicationTaskDto medicationTaskDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(medicationTask, "medicationTask provided is null");
        Assert.notNull(medicationTaskDto, "MedicationTaskDto provided is null");
        Block block = blockService.getBlockById(medicationTaskDto.blockId());
        Disease disease = diseaseService.getDiseaseById(medicationTaskDto.diseaseId());
        medicationTask.setBlock(block);
        medicationTask.setDisease(disease);
        medicationTask.setDate(medicationTaskDto.date());
        return medicationTaskRepo.save(medicationTask);
    }

    public void deleteMedicationTask(MedicationTask medicationTask) throws IllegalArgumentException {
        medicationTaskRepo.delete(medicationTask);
    }
}
