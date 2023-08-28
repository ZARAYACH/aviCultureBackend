package ma.ens.AviCultureBackend.incident.mapper;

import ma.ens.AviCultureBackend.incident.modal.Incident;
import ma.ens.AviCultureBackend.incident.modal.Sanction;
import ma.ens.AviCultureBackend.incident.modal.dto.IncidentDto;
import ma.ens.AviCultureBackend.incident.modal.dto.SanctionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IncidentMapper {

    List<IncidentDto> toIncidentDtos(List<Incident> incidents);

    @Mapping(source = "reporter.id", target = "reporterId")
    IncidentDto toIncidentDto(Incident incident);

    List<SanctionDto> toSanctionDtos(List<Sanction> sanctions);

    SanctionDto toSanctionDto(Sanction sanction);


}
