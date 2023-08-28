package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductTool;
import ma.ens.AviCultureBackend.product.modal.dto.ProductToolDto;
import ma.ens.AviCultureBackend.product.service.ProductToolService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/tool")
@RequiredArgsConstructor
public class ProductToolController {

    private final ProductToolService productToolService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductToolDto> getAllProductTool() {
        return productMapper.toProductToolDtos(productToolService.getAllProductTool());
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
