package ma.ens.AviCultureBackend.incident.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.Sanction.service.SanctionService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.incident.mapper.IncidentMapper;
import ma.ens.AviCultureBackend.incident.modal.Sanction;
import ma.ens.AviCultureBackend.incident.modal.dto.SanctionDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sanctions")
@RequiredArgsConstructor
public class SanctionController {

    private final SanctionService sanctionService;
    private final IncidentMapper mapper;

    @GetMapping
    public List<SanctionDto> getSanctionServices() {
        return mapper.toSanctionDtos(sanctionService.getAllSanction());
    }

    @GetMapping("/{sanctionId}")
    public SanctionDto getSanctionById(@PathVariable(name = "sanctionId") Long id) throws NotFoundException {
        return mapper.toSanctionDto(sanctionService
                .getSanctionById(id));
    }

    @PostMapping("/add")
    public SanctionDto addSanction(@Validated @RequestBody SanctionDto sanctionDto) throws BadRequestExeption, NotFoundException {
        try {
            return mapper.toSanctionDto(
                    sanctionService.addSanction(sanctionDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{sanctionId}/modify")
    public SanctionDto ModifySanction(@PathVariable(name = "sanctionId") Long id,
                                      @Validated @RequestBody SanctionDto sanctionDto) throws BadRequestExeption, NotFoundException {
        try {
            Sanction sanction = sanctionService.getSanctionById(id);
            return mapper.toSanctionDto(sanctionService
                    .modifySanction(sanction, sanctionDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{sanctionId}/delete")
    public void deleteSanction(@PathVariable(name = "sanctionId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            sanctionService.deleteSanction(sanctionService.getSanctionById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}

