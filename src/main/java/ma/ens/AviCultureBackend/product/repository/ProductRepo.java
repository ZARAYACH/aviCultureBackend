package ma.ens.AviCultureBackend.product.repository;

import ma.ens.AviCultureBackend.product.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface ProductRepo<T extends Product>  extends JpaRepository<T, String> {

}
