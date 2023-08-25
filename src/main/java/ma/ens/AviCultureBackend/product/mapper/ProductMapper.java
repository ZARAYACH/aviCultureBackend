package ma.ens.AviCultureBackend.product.mapper;

import ma.ens.AviCultureBackend.product.model.*;
import ma.ens.AviCultureBackend.product.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {

    List<ProductChickenDto> toProductChickenDtos(List<ProductChicken> productChickens);

    ProductChickenDto toProductChickenDto(ProductChicken productChicken);

    List<ProductBulbDto> toProductBulbDtos(List<ProductBulb> allProductBulbs);

    ProductBulbDto toProductBulbDto(ProductBulb productBulb);

    List<ProductGasCylinderDto> toProductGasCylinderDtos(List<ProductGasCylinder> allProductGasCylinders);

    ProductGasCylinderDto toProductGasCylinderDto(ProductGasCylinder productGasCylinder);

    List<ProductMedicineDto> toProductMedicineDtos(List<ProductMedicine> allProductMedicine);

    ProductMedicineDto toProductMedicineDto(ProductMedicine productMedicine);

    List<ProductStrawBalesDto> toProductStrawBalesDtos(List<ProductStrawBales> allProductStrawBales);

    ProductStrawBalesDto toProductStrawBalesDto(ProductStrawBales productStrawBales);

    List<ProductToolDto> toProductToolDtos(List<ProductTool> allProductTool);

    ProductToolDto toProductToolDto(ProductTool productTool);

    List<ToolCategorieDto> toToolCategorieDtos(List<ToolCategorie> allToolCategorie);

    ToolCategorieDto toToolCategorieDto(ToolCategorie toolCategorie);
}
