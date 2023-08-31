package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductBulb;
import ma.ens.AviCultureBackend.product.modal.dto.ProductBulbDto;
import ma.ens.AviCultureBackend.product.service.ProductBulbsService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/bulbs")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE})
public class ProductBulbController {

    private final ProductBulbsService productBulbsService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductBulbDto> getAllProductBulbs() {
        return productMapper.toProductBulbDtos(productBulbsService.getAllProductBulbs());
    }

    @PostMapping("/add")
    public ProductBulbDto addBreedingBlooks(@Validated @RequestBody ProductBulbDto productBulbDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductBulbDto(productBulbsService
                    .addProductBulb(productBulbDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductBulbDto ModifyBreedingBlocks(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductBulbDto productBulbDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductBulb ProductBulb = productBulbsService.getProductBulbById(id);
            return productMapper.toProductBulbDto(productBulbsService
                    .modifyProductBulb(ProductBulb, productBulbDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteBreedingBlock(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            productBulbsService.deleteProductBulb(productBulbsService.getProductBulbById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
