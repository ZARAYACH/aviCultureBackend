package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.FoodCategory;
import ma.ens.AviCultureBackend.product.modal.ProductFood;
import ma.ens.AviCultureBackend.product.modal.ToolCategorie;
import ma.ens.AviCultureBackend.product.modal.dto.FoodCategoryDto;
import ma.ens.AviCultureBackend.product.modal.dto.ProductFoodDto;
import ma.ens.AviCultureBackend.product.repository.FoodCategoryRepo;
import ma.ens.AviCultureBackend.product.repository.ProductFoodRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFoodService {
    private final ProductFoodRepo productFoodRepo;
    private final FoodCategoryService foodCategoryService;
    private final BuildingService buildingService;
    public List<ProductFood> getAllProductFood() {
        return productFoodRepo.findAll();
    }

    public ProductFood getProductFoodById(String id) throws NotFoundException {
        return productFoodRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Tool with id " + id + " not found"));
    }

    public ProductFood addProductFood(ProductFoodDto productFoodDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productFoodDto, "Product Straw Bales Dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productFoodDto.storageBuildingId());
        FoodCategory foodCategory = foodCategoryService.getFoodCategoryById(productFoodDto.foodCategoryId());
        return productFoodRepo.save(ProductFood.builder()
                .name(productFoodDto.name())
                .description(productFoodDto.description())
                .unitaryPrice(productFoodDto.unitaryPrice())
                .storageBuilding(storageBuilding)
                .productFoodCategory(foodCategory)
                .remarks(productFoodDto.remarks())
                .build());
    }

    public ProductFood modifyProductFood(ProductFood productFood, ProductFoodDto productFoodDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(productFood, "ProductFood provided is null");
        Assert.notNull(productFoodDto, "ProductFoodDto dto provided is null");
        Building storageBuilding = buildingService.getBuildingById(productFoodDto.storageBuildingId());
        FoodCategory foodCategory = foodCategoryService.getFoodCategoryById(productFoodDto.foodCategoryId());
        productFood.setName(productFoodDto.name());
        productFood.setDescription(productFoodDto.description());
        productFood.setUnitaryPrice(productFoodDto.unitaryPrice());
        productFood.setStorageBuilding(storageBuilding);
        productFood.setProductFoodCategory(foodCategory);
        productFood.setRemarks(productFoodDto.remarks());
        return productFoodRepo.save(productFood);
    }

    public void deleteProductFood(ProductFood productFood) throws IllegalArgumentException {
        productFoodRepo.delete(productFood);
    }
}