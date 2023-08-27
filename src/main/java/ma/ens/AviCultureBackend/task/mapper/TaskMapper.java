package ma.ens.AviCultureBackend.task.mapper;

import ma.ens.AviCultureBackend.task.modal.BulbsReplacementTask;
import ma.ens.AviCultureBackend.task.modal.MedicationTask;
import ma.ens.AviCultureBackend.task.modal.VaccinationTask;
import ma.ens.AviCultureBackend.task.modal.dto.BulbsReplacementTaskDto;
import ma.ens.AviCultureBackend.task.modal.dto.MedicationTaskDto;
import ma.ens.AviCultureBackend.task.modal.dto.VaccinationTaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TaskMapper {

    @Mapping(source = "building.id", target = "buildingId")
    BulbsReplacementTaskDto toBulbsReplacementTaskDto(BulbsReplacementTask bulbsReplacementTask);
    List<BulbsReplacementTaskDto> toBulbsReplacementTaskDtos(List<BulbsReplacementTask> bulbsReplacementTasks);


    @Mapping(source = "block.id", target = "blockId")
    @Mapping(source = "disease.id", target = "diseaseId")
    VaccinationTaskDto toVaccinationTaskDto(VaccinationTask vaccinationTask);
    List<VaccinationTaskDto> toVaccinationTaskDtos(List<VaccinationTask> vaccinationTasks);

    @Mapping(source = "block.id", target = "blockId")
    @Mapping(source = "disease.id", target = "diseaseId")
    MedicationTaskDto toMedicationTaskDto(MedicationTask medicationTask);
    List<MedicationTaskDto> toMedicationTaskDtos(List<MedicationTask> medicationTasks);

}

