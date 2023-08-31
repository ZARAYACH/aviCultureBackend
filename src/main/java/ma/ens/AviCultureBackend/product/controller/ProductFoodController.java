package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductFood;
import ma.ens.AviCultureBackend.product.modal.dto.ProductFoodDto;
import ma.ens.AviCultureBackend.product.service.ProductFoodService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/foods")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE})
public class ProductFoodController {

    private final ProductFoodService ProductFoodService;
    private final ProductMapper productMapper;

    @GetMapping
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public List<ProductFoodDto> getAllProductFood() {
        return productMapper.toProductFoodDtos(ProductFoodService.getAllProductFood());
    }
    @GetMapping("/{id}")
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public ProductFoodDto getProductFoodById(@PathVariable String id) throws NotFoundException {
        return productMapper.toProductFoodDto(ProductFoodService.getProductFoodById(id));
    }


    @PostMapping("/add")
    public ProductFoodDto addProductFood(@Validated @RequestBody ProductFoodDto productFoodDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductFoodDto(ProductFoodService
                    .addProductFood(productFoodDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductFoodDto ModifyProductFood(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductFoodDto productFoodDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductFood ProductFood = ProductFoodService.getProductFoodById(id);
            return productMapper.toProductFoodDto(ProductFoodService
                    .modifyProductFood(ProductFood, productFoodDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteProductFood(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            ProductFoodService.deleteProductFood(ProductFoodService.getProductFoodById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
