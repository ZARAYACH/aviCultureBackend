package ma.ens.AviCultureBackend.medical.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.mapper.DiseaseMapper;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.modal.dto.DiseaseDto;
import ma.ens.AviCultureBackend.medical.service.DiseaseService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diseases")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_ADMIN_VALUE})
public class DiseaseController {


    private final DiseaseService diseaseService;
    private final DiseaseMapper mapper;

    @GetMapping
    public List<DiseaseDto> getDiseaseServices() {
        return mapper.toDiseaseDtos(diseaseService.getAllDiseases());
    }

    @GetMapping("/{DiseaseId}")
    public DiseaseDto getDiseaseById(@PathVariable(name = "DiseaseId") Long id) throws NotFoundException {
        return mapper.toDiseaseDto(diseaseService
                .getDiseaseById(id));
    }

    @PostMapping("/add")
    public DiseaseDto addDisease(@Validated @RequestBody DiseaseDto DiseaseDto) throws BadRequestExeption, NotFoundException {
        try {
            return mapper.toDiseaseDto(
                    diseaseService.addDisease(DiseaseDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{DiseaseId}/modify")
    public DiseaseDto ModifyDisease(@PathVariable(name = "DiseaseId") Long id,
                                    @Validated @RequestBody DiseaseDto diseaseDto) throws BadRequestExeption, NotFoundException {
        try {
            Disease disease = diseaseService.getDiseaseById(id);
            return mapper.toDiseaseDto(diseaseService
                    .modifyDisease(disease, diseaseDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{DiseaseId}/delete")
    public void deleteDisease(@PathVariable(name = "DiseaseId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            diseaseService.deleteDisease(diseaseService.getDiseaseById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
