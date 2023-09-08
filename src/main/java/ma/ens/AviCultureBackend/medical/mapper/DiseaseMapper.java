package ma.ens.AviCultureBackend.medical.mapper;

import ma.ens.AviCultureBackend.incident.modal.Sanction;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.modal.dto.DiseaseDto;
import ma.ens.AviCultureBackend.product.modal.Product;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface DiseaseMapper {

    List<DiseaseDto> toDiseaseDtos(List<Disease> diseases);
    @Mapping(source = "medicines", target = "medicineIds")
    DiseaseDto toDiseaseDto(Disease disease);

    default List<String> mapMedicines(List<ProductMedicine> medicines) {
        if (medicines != null) {
            return medicines.stream().map(Product::getId).collect(Collectors.toList());
        }
        return null;
    }
}
