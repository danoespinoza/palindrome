package cl.danoespinoza.palindrome.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("products")
public class Product {

	@Field(name = "id")
	private Integer id;

	private String brand;
	private String description;
	private String image;
	private Integer price;

	@Transient
	private Integer discount;

	@Transient
	private Integer normalPrice;

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(Integer normalPrice) {
		this.normalPrice = normalPrice;
	}
}
