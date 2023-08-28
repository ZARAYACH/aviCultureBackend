package ma.ens.AviCultureBackend.product.repository;

import ma.ens.AviCultureBackend.product.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo<T extends Product>  extends JpaRepository<T, String> {

}
