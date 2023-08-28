package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Block;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.ProductChicken;
import ma.ens.AviCultureBackend.product.modal.dto.ProductChickenDto;
import ma.ens.AviCultureBackend.product.repository.ProductChickenRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductChickenService {
    private final ProductChickenRepo productChickenRepo;
    private final BlockService blockService;
    private final BuildingService buildingService;

    public List<ProductChicken> getAllChickenProducts() {
        return productChickenRepo.findAll();
    }

    public ProductChicken getChickenProductById(String id) throws NotFoundException {
        return productChickenRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding center with id " + id + " not found"));
    }

    public ProductChicken addChickenProduct(ProductChickenDto productChickenDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productChickenDto, "breeding center dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productChickenDto.storageBuildingId());
        Block block = blockService.getBlockById(productChickenDto.blockId());
        return productChickenRepo.save(ProductChicken.builder()
                .name(productChickenDto.name())
                .description(productChickenDto.description())
                .unitaryPrice(productChickenDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .block(block).build());
    }

    public ProductChicken modifyProductChicken(ProductChicken productChicken, ProductChickenDto productChickenDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productChicken, "Product chicken provided is null");
        Assert.notNull(productChickenDto, "Product chicken dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productChickenDto.storageBuildingId());
        Block block = blockService.getBlockById(productChickenDto.blockId());
        productChicken.setName(productChickenDto.name());
        productChicken.setDescription(productChickenDto.description());
        productChicken.setUnitaryPrice(productChickenDto.unitaryPrice());
        productChicken.setStorageBuilding(storageBuilding);
        productChicken.setBlock(block);
        return productChickenRepo.save(productChicken);
    }

    public void deleteProductChicken(ProductChicken productChicken) throws IllegalArgumentException {
        productChickenRepo.delete(productChicken);
    }
}
