package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductChicken;
import ma.ens.AviCultureBackend.product.modal.dto.ProductChickenDto;
import ma.ens.AviCultureBackend.product.service.ProductChickenService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/chickens")
@RequiredArgsConstructor
public class ProductChickenController {

    private final ProductChickenService productChickenService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductChickenDto> getAllProductChickens() {
        return productMapper.toProductChickenDtos(productChickenService.getAllChickenProducts());
    }

    @PostMapping("/add")
    public ProductChickenDto addBreedingBlooks(@Validated @RequestBody ProductChickenDto productChickenDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductChickenDto(productChickenService
                    .addChickenProduct(productChickenDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductChickenDto ModifyBreedingBlocks(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductChickenDto productChickenDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductChicken productChicken = productChickenService.getChickenProductById(id);
            return productMapper.toProductChickenDto(productChickenService
                    .modifyProductChicken(productChicken, productChickenDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteBreedingBlock(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            productChickenService.deleteProductChicken(productChickenService.getChickenProductById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
