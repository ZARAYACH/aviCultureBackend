package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.service.DiseaseService;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import ma.ens.AviCultureBackend.product.modal.dto.ProductMedicineDto;
import ma.ens.AviCultureBackend.product.repository.ProductMedicineRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMedicineService {
    private final ProductMedicineRepo productMedicineRepo;
    private final BuildingService buildingService;
    private final DiseaseService diseaseService;

    public List<ProductMedicine> getAllProductMedicine() {
        return productMedicineRepo.findAll();
    }

    public List<ProductMedicine> getAllProductMedicineVaccine() {
        return productMedicineRepo.findAllVaccine();
    }

    public ProductMedicine getProductMedicineById(String id) throws NotFoundException {
        return productMedicineRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Medicine with id " + id + " not found"));
    }

    public ProductMedicine getProductMedicineVaccineById(String id) throws NotFoundException {
        return productMedicineRepo.findVaccineById(id)
                .orElseThrow(() -> new NotFoundException("Product Medicine with id " + id + " not found"));
    }

    public ProductMedicine addProductMedicine(ProductMedicineDto productMedicineDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productMedicineDto, "ProductMedicineDto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productMedicineDto.storageBuilding().id());
        List<Disease> diseases = diseaseService.getDiseasesByIds(productMedicineDto.diseaseIds());
        return productMedicineRepo.save(ProductMedicine.builder()
                .name(productMedicineDto.name())
                .description(productMedicineDto.description())
                .unitaryPrice(productMedicineDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .diseases(diseases)
                .isVaccine(productMedicineDto.isVaccine())
                .build());
    }

    public ProductMedicine modifyProductMedicine(ProductMedicine productMedicine, ProductMedicineDto productMedicineDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productMedicine, "ProductMedicine provided is null");
        Assert.notNull(productMedicineDto, "ProductMedicineDto dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productMedicineDto.storageBuilding().id());
        List<Disease> diseases = diseaseService.getDiseasesByIds(productMedicineDto.diseaseIds());
        productMedicine.setName(productMedicineDto.name());
        productMedicine.setVaccine(productMedicine.isVaccine());
        productMedicine.setDescription(productMedicineDto.description());
        productMedicine.setUnitaryPrice(productMedicineDto.unitaryPrice());
        productMedicine.setStorageBuilding(storageBuilding);
        productMedicine.setDiseases(diseases);
        return productMedicineRepo.save(productMedicine);
    }

    public void deleteProductMedicine(ProductMedicine productMedicine) throws IllegalArgumentException {
        productMedicineRepo.delete(productMedicine);
    }

    public List<ProductMedicine> getAllProductMedicineWithIds(List<String> ids) {
        return productMedicineRepo.findAllById(ids);
    }
}