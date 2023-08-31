package ma.ens.AviCultureBackend.product.repository;

import ma.ens.AviCultureBackend.product.modal.FoodCategory;
import ma.ens.AviCultureBackend.product.modal.ToolCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepo extends JpaRepository<FoodCategory, Long> {
}
