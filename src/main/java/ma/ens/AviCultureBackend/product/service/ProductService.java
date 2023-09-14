package ma.ens.AviCultureBackend.product.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.Product;
import ma.ens.AviCultureBackend.product.repository.AllProductRepo;
import ma.ens.AviCultureBackend.product.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final AllProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(String id) throws NotFoundException {
        return productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " Not found"));
    }
    public List<Product> getProductsByIds(List<String> ids) {
        return productRepo.findAllById(ids);
    }

	public void deleteAllByIds(List<String> productId) {
	    productRepo.deleteAllById(productId);
    }
}
