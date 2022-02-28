package cl.danoespinoza.palindrome.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	private static final String regex = ".*";

	public List<Product> findAll() {
		return mongoTemplate.findAll(Product.class);
	}

	public List<Product> findById(Integer id) {
		Query query = new Query();
		
		List<Criteria> criteria = new ArrayList<>();
		criteria.add(Criteria.where("id").is(id));
		
		query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[criteria.size()])));
		
		return mongoTemplate.find(query, Product.class);
	}

	public List<Product> findByText(String searchText) {
		Query query = new Query();
		
		List<Criteria> criteria = new ArrayList<>();
		criteria.add(Criteria.where("brand").regex(regex.concat(searchText).concat(regex)));
		criteria.add(Criteria.where("description").regex(regex.concat(searchText).concat(regex)));
		
		query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[criteria.size()])));
		
		return mongoTemplate.find(query, Product.class);
	}
}
