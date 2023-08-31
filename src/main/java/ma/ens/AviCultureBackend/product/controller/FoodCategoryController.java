package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.FoodCategory;
import ma.ens.AviCultureBackend.product.modal.dto.FoodCategoryDto;
import ma.ens.AviCultureBackend.product.service.FoodCategoryService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/food-categorie")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<FoodCategoryDto> getAllFoodCategory() {
        return productMapper.toFoodCategoryDtos(foodCategoryService.getAllFoodCategory());
    }

    @PostMapping("/add")
    public FoodCategoryDto addFoodCategory(@Validated @RequestBody FoodCategoryDto FoodCategoryDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toFoodCategoryDto(foodCategoryService
                    .addFoodCategory(FoodCategoryDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{categorieId}/modify")
    public FoodCategoryDto ModifyFoodCategory(@PathVariable(name = "categorieId") Long id,
                                                @Validated @RequestBody FoodCategoryDto FoodCategoryDto) throws BadRequestExeption, NotFoundException {
        try {
            FoodCategory FoodCategory = foodCategoryService.getFoodCategoryById(id);
            return productMapper.toFoodCategoryDto(foodCategoryService
                    .modifyFoodCategory(FoodCategory, FoodCategoryDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{categorieId}/delete")
    public void deleteFoodCategory(@PathVariable(name = "categorieId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            foodCategoryService.deleteFoodCategory(foodCategoryService.getFoodCategoryById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
