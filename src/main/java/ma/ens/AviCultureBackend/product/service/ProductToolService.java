package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.ProductTool;
import ma.ens.AviCultureBackend.product.modal.ToolCategorie;
import ma.ens.AviCultureBackend.product.modal.dto.ProductToolDto;
import ma.ens.AviCultureBackend.product.repository.ProductToolRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductToolService {
    private final ProductToolRepo productToolRepo;
    private final BuildingService buildingService;
    private final ToolCategorieService toolCategorieService;

    public List<ProductTool> getAllProductTool() {
        return productToolRepo.findAll();
    }

    public ProductTool getProductToolById(String id) throws NotFoundException {
        return productToolRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Tool with id " + id + " not found"));
    }

    public ProductTool addProductTool(ProductToolDto productToolDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productToolDto, "Product Straw Bales Dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productToolDto.storageBuildingId());
        ToolCategorie toolCategorie = toolCategorieService.getToolCategorieById(productToolDto.toolCategorieId());
        return productToolRepo.save(ProductTool.builder()
                .name(productToolDto.name())
                .description(productToolDto.description())
                .unitaryPrice(productToolDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .usedFor(productToolDto.usedFor())
                .toolCategorie(toolCategorie)
                .build());
    }

    public ProductTool modifyProductTool(ProductTool productTool, ProductToolDto productToolDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productTool, "ProductTool provided is null");
        Assert.notNull(productToolDto, "ProductToolDto dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productToolDto.storageBuildingId());
        ToolCategorie toolCategorie = toolCategorieService.getToolCategorieById(productToolDto.toolCategorieId());
        productTool.setName(productToolDto.name());
        productTool.setDescription(productToolDto.description());
        productTool.setUnitaryPrice(productToolDto.unitaryPrice());
        productTool.setStorageBuilding(storageBuilding);
        productTool.setToolCategorie(toolCategorie);
        return productToolRepo.save(productTool);
    }

    public void deleteProductTool(ProductTool ProductTool) throws IllegalArgumentException {
        productToolRepo.delete(ProductTool);
    }
}