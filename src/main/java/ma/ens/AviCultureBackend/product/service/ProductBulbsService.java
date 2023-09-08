package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.ProductBulb;
import ma.ens.AviCultureBackend.product.modal.dto.ProductBulbDto;
import ma.ens.AviCultureBackend.product.repository.ProductBulbsRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBulbsService {
    private final ProductBulbsRepo productBulbsRepo;
    private final BuildingService buildingService;

    public List<ProductBulb> getAllProductBulbs() {
        return productBulbsRepo.findAll();
    }

    public ProductBulb getProductBulbById(String id) throws NotFoundException {
        return productBulbsRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Bulb with id " + id + " not found"));
    }

    public ProductBulb addProductBulb(ProductBulbDto productBulbDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productBulbDto, "breeding center dto provided is null");
        Building storageBuilding = buildingService.getStorageBuildingById(productBulbDto.storageBuilding().id());
        return productBulbsRepo.save(ProductBulb.builder()
                .name(productBulbDto.name())
                .description(productBulbDto.description())
                .unitaryPrice(productBulbDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .marque(productBulbDto.marque())
                .quantity(productBulbDto.quantity())
                .powerInWatt(productBulbDto.powerInWatt()).build());
    }

    public ProductBulb modifyProductBulb(ProductBulb productBulb, ProductBulbDto productBulbDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productBulb, "Product bulb provided is null");
        Assert.notNull(productBulbDto, "Product bulb dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productBulbDto.storageBuilding().id());
        productBulb.setName(productBulbDto.name());
        productBulb.setDescription(productBulbDto.description());
        productBulb.setUnitaryPrice(productBulbDto.unitaryPrice());
        productBulb.setStorageBuilding(storageBuilding);
        productBulb.setQuantity(productBulbDto.quantity());
        productBulb.setPowerInWatt(productBulb.getPowerInWatt());
        productBulb.setMarque(productBulb.getMarque());
        return productBulbsRepo.save(productBulb);
    }

    public void deleteProductBulb(ProductBulb productBulb) throws IllegalArgumentException {
        productBulbsRepo.delete(productBulb);
    }
}
