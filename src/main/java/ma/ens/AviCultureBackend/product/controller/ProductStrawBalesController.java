package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.model.ProductStrawBales;
import ma.ens.AviCultureBackend.product.model.dto.ProductStrawBalesDto;
import ma.ens.AviCultureBackend.product.service.ProductStrawBalesService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/straw-bales")
@RequiredArgsConstructor
public class ProductStrawBalesController {

    private final ProductStrawBalesService productStrawBalesService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductStrawBalesDto> getAllProductStrawBales() {
        return productMapper.toProductStrawBalesDtos(productStrawBalesService.getAllProductStrawBales());
    }

    @PostMapping("/add")
    public ProductStrawBalesDto addProductStrawBales(@Validated @RequestBody ProductStrawBalesDto ProductStrawBalesDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductStrawBalesDto(productStrawBalesService
                    .addProductStrawBales(ProductStrawBalesDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductStrawBalesDto ModifyProductStrawBales(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductStrawBalesDto productStrawBalesDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductStrawBales productStrawBales = productStrawBalesService.getProductStrawBalesById(id);
            return productMapper.toProductStrawBalesDto(productStrawBalesService
                    .modifyProductStrawBales(productStrawBales, productStrawBalesDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteProductStrawBales(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            productStrawBalesService.deleteProductStrawBales(productStrawBalesService.getProductStrawBalesById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
