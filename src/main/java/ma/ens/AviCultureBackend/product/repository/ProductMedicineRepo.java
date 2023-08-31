package ma.ens.AviCultureBackend.product.repository;

import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductMedicineRepo extends ProductRepo<ProductMedicine> {

    @Query("SELECT pm FROM ProductMedicine pm WHERE pm.isVaccine = true AND pm.id = :id")
    Optional<ProductMedicine> findVaccineById(String id);
    @Query("SELECT pm FROM ProductMedicine pm WHERE pm.isVaccine = true")
    List<ProductMedicine> findAllVaccine();
}
