package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import ma.ens.AviCultureBackend.product.modal.dto.ProductMedicineDto;
import ma.ens.AviCultureBackend.product.service.ProductMedicineService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/Medicines")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE})
public class ProductMedicineController {

    private final ProductMedicineService productMedicineService;
    private final ProductMapper productMapper;

    @GetMapping
    @Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
    public List<ProductMedicineDto> getAllProductMedicines() {
        return productMapper.toProductMedicineDtos(productMedicineService.getAllProductMedicine());
    }

    @GetMapping("/{id}")
    public ProductMedicineDto getProductMedicineById(@PathVariable String id) throws NotFoundException {
        return productMapper.toProductMedicineDto(productMedicineService.getProductMedicineById(id));
    }

    @PostMapping("/add")
    public ProductMedicineDto addBreedingBlooks(@Validated @RequestBody ProductMedicineDto productMedicineDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toProductMedicineDto(productMedicineService
                    .addProductMedicine(productMedicineDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{productId}/modify")
    public ProductMedicineDto ModifyBreedingBlocks(@PathVariable(name = "productId") String id,
                                                  @Validated @RequestBody ProductMedicineDto productMedicineDto) throws BadRequestExeption, NotFoundException {
        try {
            ProductMedicine productMedicine = productMedicineService.getProductMedicineById(id);
            return productMapper.toProductMedicineDto(productMedicineService
                    .modifyProductMedicine(productMedicine, productMedicineDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteBreedingBlock(@PathVariable(name = "productId") String id) throws BadRequestExeption, NotFoundException {
        try {
            productMedicineService.deleteProductMedicine(productMedicineService.getProductMedicineById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
