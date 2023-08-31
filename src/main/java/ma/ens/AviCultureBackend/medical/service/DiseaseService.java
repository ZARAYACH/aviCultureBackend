package ma.ens.AviCultureBackend.medical.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.modal.dto.DiseaseDto;
import ma.ens.AviCultureBackend.medical.repository.DiseaseRepo;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import ma.ens.AviCultureBackend.product.service.ProductMedicineService;
import ma.ens.AviCultureBackend.user.modal.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepo diseaseRepo;
    private final ProductMedicineService productMedicineService;

    public Disease getDiseaseById(Long id) throws NotFoundException {
        return diseaseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("disease with id " + id + " not found"));
    }

    public List<Disease> getDiseasesByIds(List<Long> ids) throws NotFoundException {
        return diseaseRepo.findAllById(ids);
    }

    public Disease addDisease(DiseaseDto diseaseDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(diseaseDto, "DiseaseDto provided is null");
        List<ProductMedicine> medicines = productMedicineService.getAllProductMedicineWithIds(diseaseDto.medicineIds());
        return diseaseRepo.save(Disease.builder()
                .name(diseaseDto.name())
                .medicines(medicines)
                .description(diseaseDto.description())
                .build());
    }

    public Disease modifyDisease(Disease disease, DiseaseDto diseaseDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(disease, "Disease provided is null");
        Assert.notNull(diseaseDto, "DiseaseDto provided is null");
        List<ProductMedicine> medicines = productMedicineService.getAllProductMedicineWithIds(diseaseDto.medicineIds());
        disease.setMedicines(medicines);
        disease.setDescription(diseaseDto.description());
        disease.setName(diseaseDto.name());
        return diseaseRepo.save(disease);
    }

    public void deleteDisease(Disease disease) throws IllegalArgumentException {
        diseaseRepo.delete(disease);
    }

    public List<Disease> getAllDiseases() {
        return diseaseRepo.findAll();
    }
}
