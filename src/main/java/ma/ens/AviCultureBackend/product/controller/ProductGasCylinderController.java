package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.model.ProductGasCylinder;
import ma.ens.AviCultureBackend.product.model.dto.ProductGasCylinderDto;
import ma.ens.AviCultureBackend.product.service.ProductGasCylinderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/gas-cylinder")
@RequiredArgsConstructor
public class ProductGasCylinderController {

    private final ProductGasCylinderService productGasCylinderService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductGasCylinderDto> getAllProductGasCylinders() {
        return productMapper.toProductGasCylinderDtos(productGasCylinderService.getAllProductGasCylinders());
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
