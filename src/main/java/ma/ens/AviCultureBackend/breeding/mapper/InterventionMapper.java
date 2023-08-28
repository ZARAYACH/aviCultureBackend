package ma.ens.AviCultureBackend.breeding.mapper;

import ma.ens.AviCultureBackend.breeding.modal.Intervention;
import ma.ens.AviCultureBackend.breeding.modal.dto.InterventionDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface InterventionMapper {

    InterventionDto toInterventionDto(Intervention intervention);

    List<InterventionDto> toInterventionDtos(List<Intervention> interventionByBuilding);

}
