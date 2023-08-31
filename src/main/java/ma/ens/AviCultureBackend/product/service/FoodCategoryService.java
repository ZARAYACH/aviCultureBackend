package ma.ens.AviCultureBackend.product.service;

import lombok.AllArgsConstructor;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.service.DiseaseService;
import ma.ens.AviCultureBackend.product.modal.FoodCategory;
import ma.ens.AviCultureBackend.product.modal.dto.FoodCategoryDto;
import ma.ens.AviCultureBackend.product.repository.FoodCategoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodCategoryService {

    private final FoodCategoryRepo foodCategoryRepo;

    public List<FoodCategory> getAllFoodCategory() {
        return foodCategoryRepo.findAll();
    }

    public FoodCategory getFoodCategoryById(Long id) throws NotFoundException {
        return foodCategoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fool Categorie with id " + id + " not found"));
    }

    public FoodCategory addFoodCategory(FoodCategoryDto FoodCategoryDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(FoodCategoryDto, "Product Tool Dto provided is null");
        return foodCategoryRepo.save(FoodCategory.builder()
                .name(FoodCategoryDto.name())
                .build());
    }

    public FoodCategory modifyFoodCategory(FoodCategory FoodCategory, FoodCategoryDto FoodCategoryDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(FoodCategory, "FoodCategory provided is null");
        Assert.notNull(FoodCategoryDto, "FoodCategoryDto dto provided is null");
        FoodCategory.setName(FoodCategoryDto.name());
        return foodCategoryRepo.save(FoodCategory);
    }

    public void deleteFoodCategory(FoodCategory FoodCategory) throws IllegalArgumentException {
        foodCategoryRepo.delete(FoodCategory);
    }
}
