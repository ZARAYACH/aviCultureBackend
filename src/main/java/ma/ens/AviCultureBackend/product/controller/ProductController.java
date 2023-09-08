package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.modal.Product;
import ma.ens.AviCultureBackend.product.service.ProductService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_DIRECTOR_VALUE})
public class ProductController {

    private final ProductService productService;

    @GetMapping
    private List<Product> getAllProducts(@RequestParam(name = "productId") String productId) throws NotFoundException {
        if (productId != null) {
            return List.of(productService.getProductById(productId));
        }
        return productService.getAllProducts();
    }

}