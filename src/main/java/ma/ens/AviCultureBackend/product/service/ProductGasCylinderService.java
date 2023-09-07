package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.ProductGasCylinder;
import ma.ens.AviCultureBackend.product.modal.dto.ProductGasCylinderDto;
import ma.ens.AviCultureBackend.product.repository.ProductGasCylinderRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductGasCylinderService {
    private final ProductGasCylinderRepo productGasCylindersRepo;
    private final BuildingService buildingService;

    public List<ProductGasCylinder> getAllProductGasCylinders() {
        return productGasCylindersRepo.findAll();
    }

    public ProductGasCylinder getProductGasCylinderById(String id) throws NotFoundException {
        return productGasCylindersRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("product Gas Cylinder with id " + id + " not found"));
    }

    public ProductGasCylinder addProductGasCylinder(ProductGasCylinderDto productGasCylinderDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productGasCylinderDto, "productGasCylinderDto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productGasCylinderDto.storageBuilding().id());
        return productGasCylindersRepo.save(ProductGasCylinder.builder()
                .name(productGasCylinderDto.name())
                .description(productGasCylinderDto.description())
                .unitaryPrice(productGasCylinderDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .type(productGasCylinderDto.type()).build());
    }

    public ProductGasCylinder modifyProductGasCylinder(ProductGasCylinder productGasCylinder, ProductGasCylinderDto productGasCylinderDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productGasCylinder, "productGasCylinder provided is null");
        Assert.notNull(productGasCylinderDto, "productGasCylinderDto dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productGasCylinderDto.storageBuilding().id());
        productGasCylinder.setName(productGasCylinderDto.name());
        productGasCylinder.setDescription(productGasCylinderDto.description());
        productGasCylinder.setUnitaryPrice(productGasCylinderDto.unitaryPrice());
        productGasCylinder.setStorageBuilding(storageBuilding);
        productGasCylinder.setType(productGasCylinder.getType());
        return productGasCylindersRepo.save(productGasCylinder);
    }

    public void deleteProductGasCylinder(ProductGasCylinder productGasCylinder) throws IllegalArgumentException {
        productGasCylindersRepo.delete(productGasCylinder);
    }
}
