package ma.ens.AviCultureBackend.product.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.product.mapper.ProductMapper;
import ma.ens.AviCultureBackend.product.modal.dto.ProductDto;
import ma.ens.AviCultureBackend.product.service.ProductService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	private final ProductMapper mapper;

	@GetMapping
	private List<ProductDto> getAllProducts(@RequestParam(name = "productId", required = false) String productId) throws NotFoundException {
		if (productId != null) {
			return mapper.toProductDtos(List.of(productService.getProductById(productId)));
		}
		return mapper.toProductDtos(productService.getAllProducts());
	}

	@DeleteMapping("/delete")
	private void deleteProducts(@RequestParam(name = "productIds") List<String> productId) throws NotFoundException {
		productService.deleteAllByIds(productId);
	}

}