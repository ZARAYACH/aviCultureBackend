package ma.ens.AviCultureBackend.breeding.mapper;

import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.breeding.modal.dto.BreedingCenterDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BreedingCenterMapper {
    BreedingCenterDto toBreedingCenterDto(BreedingCenter allBredingCenter);
    List<BreedingCenterDto> toBreedingCenterDtos(List<BreedingCenter> allBredingCenters);

    BreedingCenter toBreedingCenter(BreedingCenterDto allBredingCenter);

}
