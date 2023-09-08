package ma.ens.AviCultureBackend.product.repository;

import ma.ens.AviCultureBackend.product.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllProductRepo  extends ProductRepo<Product> {

}
