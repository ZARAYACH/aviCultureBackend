package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductGasCylinder;
import ma.ens.AviCultureBackend.product.modal.dto.ProductChickenDto;
import ma.ens.AviCultureBackend.product.modal.dto.ProductGasCylinderDto;
import ma.ens.AviCultureBackend.product.service.ProductGasCylinderService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/gas-cylinders")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE})
public class ProductGasCylinderController {

    private final ProductGasCylinderService productGasCylinderService;
    private final ProductMapper productMapper;

    @GetMapping
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public List<ProductGasCylinderDto> getAllProductGasCylinders() {
        return productMapper.toProductGasCylinderDtos(productGasCylinderService.getAllProductGasCylinders());
    }

    @GetMapping("/{id}")
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public ProductGasCylinderDto getProductGasCylindersById(@PathVariable String id) throws NotFoundException {
        return productMapper.toProductGasCylinderDto(productGasCylinderService.getProductGasCylinderById(id));
    }

    @PostMapping("/add")
    public ProductGasCylinderDto addBreedingBlooks(@Validated @RequestBody ProductGasCylinderDto ProductGasCylinderDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductGasCylinderDto(productGasCylinderService
                    .addProductGasCylinder(ProductGasCylinderDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductGasCylinderDto ModifyBreedingBlocks(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductGasCylinderDto ProductGasCylinderDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductGasCylinder ProductGasCylinder = productGasCylinderService.getProductGasCylinderById(id);
            return productMapper.toProductGasCylinderDto(productGasCylinderService
                    .modifyProductGasCylinder(ProductGasCylinder, ProductGasCylinderDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteBreedingBlock(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            productGasCylinderService.deleteProductGasCylinder(productGasCylinderService.getProductGasCylinderById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
