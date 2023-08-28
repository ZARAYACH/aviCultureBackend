package ma.ens.AviCultureBackend.breeding.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.modal.dto.BreedingCenterDto;
import ma.ens.AviCultureBackend.breeding.repository.BreedingCenterRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedingCenterService {

    private final BreedingCenterRepo breedingCenterRepo;

    public List<BreedingCenter> getAllBredingCenters() {
        return breedingCenterRepo.findAll();
    }

    public BreedingCenter getBreedingCenterById(Long id) throws NotFoundException {
        return breedingCenterRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding center with id " + id + " not found"));
    }

    public BreedingCenter addBreadingCenter(BreedingCenterDto breedingCenterDto) throws IllegalArgumentException {
        Assert.notNull(breedingCenterDto, "breeding center dto provided is null");
        return breedingCenterRepo.save(BreedingCenter.builder()
                .name(breedingCenterDto.name())
                .address(breedingCenterDto.address()).build());
    }

    public BreedingCenter modifyBreadingCenter(BreedingCenter breedingCenter, BreedingCenterDto breedingCenterDto) {
        Assert.notNull(breedingCenter, "breeding center provided is null");
        breedingCenter.setName(breedingCenter.getName());
        breedingCenter.setAddress(breedingCenter.getAddress());
        return breedingCenterRepo.save(breedingCenter);
    }

    public void deleteBreedingCenter(BreedingCenter breedingCenter) throws IllegalArgumentException {
        breedingCenterRepo.delete(breedingCenter);
    }
}
