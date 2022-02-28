package cl.danoespinoza.palindrome.domain;

import java.util.List;

public interface ProductRepository {

	public List<Product> findAll();

	public List<Product> findById(Integer id);

	public List<Product> findByText(String searchText);
}
