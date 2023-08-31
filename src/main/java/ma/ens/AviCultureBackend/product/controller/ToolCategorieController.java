package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.ToolCategorie;
import ma.ens.AviCultureBackend.product.modal.dto.ToolCategorieDto;
import ma.ens.AviCultureBackend.product.service.ToolCategorieService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/tool-categorie")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
public class ToolCategorieController {

    private final ToolCategorieService ToolCategorieService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ToolCategorieDto> getAllToolCategorie() {
        return productMapper.toToolCategorieDtos(ToolCategorieService.getAllToolCategorie());
    }

    @PostMapping("/add")
    public ToolCategorieDto addToolCategorie(@Validated @RequestBody ToolCategorieDto ToolCategorieDto) throws BadRequestExeption, NotFoundException {
        try {
            return productMapper.toToolCategorieDto(ToolCategorieService
                    .addToolCategorie(ToolCategorieDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{categorieId}/modify")
    public ToolCategorieDto ModifyToolCategorie(@PathVariable(name = "categorieId") Long id,
                                                @Validated @RequestBody ToolCategorieDto ToolCategorieDto) throws BadRequestExeption, NotFoundException {
        try {
            ToolCategorie ToolCategorie = ToolCategorieService.getToolCategorieById(id);
            return productMapper.toToolCategorieDto(ToolCategorieService
                    .modifyToolCategorie(ToolCategorie, ToolCategorieDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{categorieId}/delete")
    public void deleteToolCategorie(@PathVariable(name = "categorieId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            ToolCategorieService.deleteToolCategorie(ToolCategorieService.getToolCategorieById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
