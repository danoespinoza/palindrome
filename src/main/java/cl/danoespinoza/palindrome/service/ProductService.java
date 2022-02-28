package cl.danoespinoza.palindrome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.danoespinoza.palindrome.domain.Product;
import cl.danoespinoza.palindrome.domain.ProductRepository;
import cl.danoespinoza.palindrome.exception.IncompleteSearchTextException;

@Service
@Transactional
public class ProductService {

	public static final Integer DISCOUNT_PERCENT = 50;

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Product>> getProductById(Integer id) {
		List<Product> products = productRepository.findById(id);

		if (isPalindrome(id)) {
			applyDiscount(products);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	public ResponseEntity<List<Product>> getProductsByText(String searchText) {
		if (searchText.length() < 4)
			throw new IncompleteSearchTextException();

		List<Product> products = productRepository.findByText(searchText);

		if (isPalindrome(searchText)) {
			applyDiscount(products);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	private void applyDiscount(List<Product> products) {
		products.stream().forEach(product -> {
			Integer price = product.getPrice();
			product.setDiscount(DISCOUNT_PERCENT);
			product.setNormalPrice(price);
			product.setPrice(price - ((price * DISCOUNT_PERCENT) / 100));
		});
	}

	protected boolean isPalindrome(String strToCheck) {
		int i = 0, j = strToCheck.length() - 1;

		while (i < j) {
			if (strToCheck.charAt(i) != strToCheck.charAt(j))
				return false;

			i++;
			j--;
		}

		return true;
	}

	protected boolean isPalindrome(Integer numToCheck) {
		int reversedNum = 0, remainder;
		int originalNum = numToCheck;

		while (numToCheck != 0) {
			remainder = numToCheck % 10;
			reversedNum = reversedNum * 10 + remainder;
			numToCheck /= 10;
		}

		if (originalNum == reversedNum) {
			return true;
		} else {
			return false;
		}
	}
}
