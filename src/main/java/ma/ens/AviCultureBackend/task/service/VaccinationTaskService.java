package ma.ens.AviCultureBackend.task.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Block;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.service.DiseaseService;
import ma.ens.AviCultureBackend.task.modal.VaccinationTask;
import ma.ens.AviCultureBackend.task.modal.dto.VaccinationTaskDto;
import ma.ens.AviCultureBackend.task.repo.VaccinationTaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationTaskService {
    private final VaccinationTaskRepo vaccinationTaskRepo;
    private final BlockService blockService;
    private final DiseaseService diseaseService;

    public List<VaccinationTask> getVaccinationTask() {
        return vaccinationTaskRepo.findAll();
    }

    public VaccinationTask getVaccinationTaskById(Long id) throws NotFoundException {
        return vaccinationTaskRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaccination Task with id " + id + " not found"));
    }

    public VaccinationTask addVaccinationTask(VaccinationTaskDto vaccinationTaskDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(vaccinationTaskDto, "Vaccination Task Dto provided is null");
        Block block = blockService.getBlockById(vaccinationTaskDto.blockId());
        Disease disease = diseaseService.getDiseaseById(vaccinationTaskDto.diseaseId());
        return vaccinationTaskRepo.save(VaccinationTask.builder()
                .block(block)
                .disease(disease)
                .date(vaccinationTaskDto.date())
                .type(vaccinationTaskDto.type())
                .build());
    }

    public VaccinationTask modifyVaccinationTask(VaccinationTask vaccinationTask, VaccinationTaskDto vaccinationTaskDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(vaccinationTask, "vaccinationTask provided is null");
        Assert.notNull(vaccinationTaskDto, "vaccinationTaskDto provided is null");
        Block block = blockService.getBlockById(vaccinationTaskDto.blockId());
        Disease disease = diseaseService.getDiseaseById(vaccinationTaskDto.diseaseId());
        vaccinationTask.setBlock(block);
        vaccinationTask.setDisease(disease);
        vaccinationTask.setType(vaccinationTaskDto.type());
        vaccinationTask.setDate(vaccinationTaskDto.date());
        return vaccinationTaskRepo.save(vaccinationTask);
    }

    public void deleteVaccinationTask(VaccinationTask vaccinationTask) throws IllegalArgumentException {
        vaccinationTaskRepo.delete(vaccinationTask);
    }
}
