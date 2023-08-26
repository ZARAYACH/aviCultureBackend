package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.model.Product;
import ma.ens.AviCultureBackend.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    private List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{productId}")
    private Product getProductWithId(@PathVariable String productId) throws NotFoundException {
        return productService.getProductById(productId);
    }
}
