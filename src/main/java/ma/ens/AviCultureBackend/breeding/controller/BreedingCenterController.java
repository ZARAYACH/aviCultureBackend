package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BreedingCenterMapper;
import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.modal.dto.BreedingCenterDto;
import ma.ens.AviCultureBackend.breeding.service.BreedingCenterService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-centers")
@RestController
@RequiredArgsConstructor
@Secured(UserRole.Role.ROLE_MANAGER_VALUE)
public class BreedingCenterController {

    private final BreedingCenterService breedingCenterService;
    private final BreedingCenterMapper breedingCenterMapper;

    @GetMapping
    public List<BreedingCenterDto> getAllBreedingCenters() {
        return breedingCenterMapper.toBreedingCenterDtos(breedingCenterService.getAllBredingCenters());
    }
    @PostMapping("/add")
    public BreedingCenterDto addBreedingCenter(@Validated @RequestBody BreedingCenterDto breedingCenterDto) throws BadRequestExeption {
        try {
            return breedingCenterMapper.toBreedingCenterDto(
                    breedingCenterService.addBreadingCenter(breedingCenterDto));
        } catch (IllegalArgumentException e){
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{breedingCenterId}/modify")
    public BreedingCenterDto ModifyBreedingCenter(@PathVariable(name = "breedingCenterId") Long id,
                                                  @Validated @RequestBody BreedingCenterDto breedingCenterDto) throws BadRequestExeption, NotFoundException {
        try {
            BreedingCenter breedingCenter = breedingCenterService.getBreedingCenterById(id);
            return breedingCenterMapper.toBreedingCenterDto(breedingCenterService
                    .modifyBreadingCenter(breedingCenter, breedingCenterDto));
        } catch (IllegalArgumentException e){
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{breedingCenterId}/delete")
    public void deleteBreedingCenter(@PathVariable(name = "breedingCenterId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            breedingCenterService.deleteBreedingCenter(breedingCenterService.getBreedingCenterById(id));
        } catch (IllegalArgumentException e){
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
