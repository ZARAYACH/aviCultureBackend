package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.ProductStrawBales;
import ma.ens.AviCultureBackend.product.modal.dto.ProductStrawBalesDto;
import ma.ens.AviCultureBackend.product.repository.ProductStrawBalesRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStrawBalesService {
    private final ProductStrawBalesRepo productStrawBalesRepo;
    private final BuildingService buildingService;

    public List<ProductStrawBales> getAllProductStrawBales() {
        return productStrawBalesRepo.findAll();
    }

    public ProductStrawBales getProductStrawBalesById(String id) throws NotFoundException {
        return productStrawBalesRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Straw Bales  with id " + id + " not found"));
    }

    public ProductStrawBales addProductStrawBales(ProductStrawBalesDto productStrawBalesDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productStrawBalesDto, "Product Straw Bales Dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productStrawBalesDto.storageBuilding().id());
        return productStrawBalesRepo.save(ProductStrawBales.builder()
                .name(productStrawBalesDto.name())
                .description(productStrawBalesDto.description())
                .unitaryPrice(productStrawBalesDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .type(productStrawBalesDto.type()).build());
    }

    public ProductStrawBales modifyProductStrawBales(ProductStrawBales productStrawBales, ProductStrawBalesDto productStrawBalesDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productStrawBales, "ProductStrawBales provided is null");
        Assert.notNull(productStrawBalesDto, "ProductStrawBalesDto dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productStrawBalesDto.storageBuilding().id());
        productStrawBales.setName(productStrawBalesDto.name());
        productStrawBales.setDescription(productStrawBalesDto.description());
        productStrawBales.setUnitaryPrice(productStrawBalesDto.unitaryPrice());
        productStrawBales.setStorageBuilding(storageBuilding);
        productStrawBales.setType(productStrawBalesDto.type());
        return productStrawBalesRepo.save(productStrawBales);
    }

    public void deleteProductStrawBales(ProductStrawBales productStrawBales) throws IllegalArgumentException {
        productStrawBalesRepo.delete(productStrawBales);
    }
}