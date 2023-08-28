package ma.ens.AviCultureBackend.product.service;

import lombok.AllArgsConstructor;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.breeding.service.BuildingService;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.service.DiseaseService;
import ma.ens.AviCultureBackend.product.modal.ToolCategorie;
import ma.ens.AviCultureBackend.product.modal.dto.ToolCategorieDto;
import ma.ens.AviCultureBackend.product.repository.ToolCategorieRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@AllArgsConstructor
public class ToolCategorieService {

    private final ToolCategorieRepo ToolCategorieRepo;
    private final BlockService blockService;
    private final BuildingService buildingService;
    private final DiseaseService diseaseService;

    public List<ToolCategorie> getAllToolCategorie() {
        return ToolCategorieRepo.findAll();
    }

    public ToolCategorie getToolCategorieById(Long id) throws NotFoundException {
        return ToolCategorieRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Tool Categorie with id " + id + " not found"));
    }

    public ToolCategorie addToolCategorie(ToolCategorieDto toolCategorieDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(toolCategorieDto, "Product Tool Dto provided is null");
        return ToolCategorieRepo.save(ToolCategorie.builder()
                .name(toolCategorieDto.name())
                .build());
    }

    public ToolCategorie modifyToolCategorie(ToolCategorie toolCategorie, ToolCategorieDto toolCategorieDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(toolCategorie, "ToolCategorie provided is null");
        Assert.notNull(toolCategorieDto, "toolCategorieDto dto provided is null");
        toolCategorie.setName(toolCategorieDto.name());
        return ToolCategorieRepo.save(toolCategorie);
    }

    public void deleteToolCategorie(ToolCategorie toolCategorie) throws IllegalArgumentException {
        ToolCategorieRepo.delete(toolCategorie);
    }
}
