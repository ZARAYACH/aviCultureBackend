package ma.ens.AviCultureBackend.product.mapper;

import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.product.modal.*;
import ma.ens.AviCultureBackend.product.modal.dto.*;
import ma.ens.AviCultureBackend.task.modal.MedicationTask;
import org.aspectj.weaver.ast.Literal;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ProductMapper {

    List<ProductChickenDto> toProductChickenDtos(List<ProductChicken> productChickens);

    ProductChickenDto toProductChickenDto(ProductChicken productChicken);

    List<ProductBulbDto> toProductBulbDtos(List<ProductBulb> allProductBulbs);

    ProductBulbDto toProductBulbDto(ProductBulb productBulb);

    List<ProductGasCylinderDto> toProductGasCylinderDtos(List<ProductGasCylinder> allProductGasCylinders);

    ProductGasCylinderDto toProductGasCylinderDto(ProductGasCylinder productGasCylinder);

    List<ProductMedicineDto> toProductMedicineDtos(List<ProductMedicine> allProductMedicine);

    @Mapping(source = "diseases", target = "diseaseIds")
    ProductMedicineDto toProductMedicineDto(ProductMedicine productMedicine);

    List<ProductStrawBalesDto> toProductStrawBalesDtos(List<ProductStrawBales> allProductStrawBales);

    ProductStrawBalesDto toProductStrawBalesDto(ProductStrawBales productStrawBales);

    List<ProductToolDto> toProductToolDtos(List<ProductTool> allProductTool);

    ProductToolDto toProductToolDto(ProductTool productTool);

    List<ToolCategorieDto> toToolCategorieDtos(List<ToolCategorie> allToolCategorie);

    ToolCategorieDto toToolCategorieDto(ToolCategorie toolCategorie);

    List<ProductFoodDto> toProductFoodDtos(List<ProductFood> productFoods);

    ProductFoodDto toProductFoodDto(ProductFood productFood);

    List<FoodCategoryDto> toFoodCategoryDtos(List<FoodCategory> foodCategories);

    FoodCategoryDto toFoodCategoryDto(FoodCategory foodCategory);

    default List<Long> mapDiseases(List<Disease> diseases) {
        if (diseases == null) {
            return new ArrayList<>();
        }
        return diseases.stream().map(Disease::getId).collect(Collectors.toList());
    }

    default List<String> mapProductMedicines(List<ProductMedicine> medicines) {
        if (medicines == null) {
            return new ArrayList<>();
        }
        ;
        return medicines.stream().map(Product::getId).collect(Collectors.toList());
    }

	List<ProductDto> toProductDtos(List<Product> productById);
    ProductDto toProductsDto(Product product);
}
