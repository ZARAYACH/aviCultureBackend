package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BreedingCenterMapper;
import ma.ens.AviCultureBackend.breeding.model.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.model.BreedingCenterDto;
import ma.ens.AviCultureBackend.breeding.service.BreedingCenterService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-centers")
@RestController
@RequiredArgsConstructor
public class BreedingCenterController {

    private final BreedingCenterService breedingCenterService;
    private final BreedingCenterMapper breedingCenterMapper;

    @GetMapping
    public List<BreedingCenterDto> getAllBreedingCenters() {
        return breedingCenterMapper.toBreedingCenterDtos(breedingCenterService.getAllBredingCenters());
    }
    @PostMapping("/add")
    public BreedingCenterDto ModifyBreedingCenter(@RequestBody BreedingCenterDto breedingCenterDto) throws BadRequestExeption {
        try {
            return breedingCenterMapper.toBreedingCenterDto(breedingCenterService
                    .addBreadingCenter(breedingCenterMapper.toBreedingCenter(breedingCenterDto)));
        } catch (IllegalArgumentException e){
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{breedingCenterId}/modify")
    public BreedingCenterDto ModifyBreedingCenter(@PathVariable(name = "breedingCenterId") Long id,
                                                  @RequestBody BreedingCenterDto breedingCenterDto) throws BadRequestExeption, NotFoundException {
        try {
            BreedingCenter breedingCenter = breedingCenterService.getBreedingCenterById(id);
            return breedingCenterMapper.toBreedingCenterDto(breedingCenterService
                    .modifyBreadingCenter(breedingCenter, breedingCenterDto));
        } catch (IllegalArgumentException e){
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{breedingCenterId}/delete")
    public void ModifyBreedingCenter(@PathVariable(name = "breedingCenterId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            breedingCenterService.deleteBreedingCenter(breedingCenterService.getBreedingCenterById(id));
        } catch (IllegalArgumentException e){
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
