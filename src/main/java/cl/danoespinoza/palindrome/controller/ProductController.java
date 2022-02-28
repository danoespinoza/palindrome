package cl.danoespinoza.palindrome.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.danoespinoza.palindrome.domain.Product;
import cl.danoespinoza.palindrome.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	ProductService productService;    

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping(value = {
			"/getProducts",
			"/getProducts/{search}"
	}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProductById(@PathVariable(name = "search", required = false) String search) {
		log.info("/api/getProduct/{}", search);
		if (search != null) {
			try {
				Integer searchInteger = Integer.valueOf(search);
				return productService.getProductById(searchInteger);
			} catch (Exception e) {
				log.info("search text is not an Integer");
				return productService.getProductsByText((String) search);
			}
		}
		else
			return productService.getProducts();
	}
}
