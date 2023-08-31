package ma.ens.AviCultureBackend.medical.mapper;

import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.modal.dto.DiseaseDto;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DiseaseMapper {

    List<DiseaseDto> toDiseaseDtos(List<Disease> diseases);

    DiseaseDto toDiseaseDto(Disease disease);

    default String mapMedicine(ProductMedicine medicine) {
        if (medicine != null) {
            return medicine.getId();
        }
        return null;
    }
}
