package ma.ens.AviCultureBackend.breeding.mapper;

import ma.ens.AviCultureBackend.breeding.model.Building;
import ma.ens.AviCultureBackend.breeding.model.BuildingDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BuildingMapper {
    BuildingDto toBuildingDto(Building building);

    List<BuildingDto> toBuildingDtos(List<Building> buildings);

    Building toBuilding(BuildingDto buildingDto);

}
