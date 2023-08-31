package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductTool;
import ma.ens.AviCultureBackend.product.modal.dto.ProductToolDto;
import ma.ens.AviCultureBackend.product.service.ProductToolService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/tools")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE})
public class ProductToolController {

    private final ProductToolService productToolService;
    private final ProductMapper productMapper;

    @GetMapping
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public List<ProductToolDto> getAllProductTool() {
        return productMapper.toProductToolDtos(productToolService.getAllProductTool());
    }
    @GetMapping("/{id}")
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public ProductToolDto getProductToolById(@PathVariable String id) throws NotFoundException {
        return productMapper.toProductToolDto(productToolService.getProductToolById(id));
    }


    @PostMapping("/add")
    public ProductToolDto addProductTool(@Validated @RequestBody ProductToolDto productToolDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductToolDto(productToolService
                    .addProductTool(productToolDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductToolDto ModifyProductTool(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductToolDto productToolDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductTool ProductTool = productToolService.getProductToolById(id);
            return productMapper.toProductToolDto(productToolService
                    .modifyProductTool(ProductTool, productToolDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteProductTool(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            productToolService.deleteProductTool(productToolService.getProductToolById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
