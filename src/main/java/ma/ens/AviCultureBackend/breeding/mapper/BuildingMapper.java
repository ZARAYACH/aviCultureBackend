package ma.ens.AviCultureBackend.breeding.mapper;

import ma.ens.AviCultureBackend.breeding.model.Building;
import ma.ens.AviCultureBackend.breeding.model.dto.BuildingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BuildingMapper {
    @Mapping(source = "breedingCenter.id", target = "breedingCenterId")
    BuildingDto toBuildingDto(Building building);

    List<BuildingDto> toBuildingDtos(List<Building> buildings);

    Building toBuilding(BuildingDto buildingDto);

}
