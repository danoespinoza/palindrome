package cl.danoespinoza.palindrome.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.danoespinoza.palindrome.domain.Product;
import cl.danoespinoza.palindrome.exception.IncompleteSearchTextException;

@SpringBootTest 
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired 
    private MockMvc mockMvc;
	
	@Test
	void whenGetAllProducts_thenAccept() throws Exception {
		String uri = "/api/getProducts";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
	}
	
	@Test
	void whenGetProductByIdPalindrome_thenAccept() throws Exception {
		String uri = "/api/getProducts/181";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = mapFromJson(content, Product[].class);
		assertTrue(productlist.length == 1);
		
		Product product = productlist[0];
		assertNotNull(product.getDiscount());
		assertNotNull(product.getNormalPrice());
	}
	
	@Test
	void whenGetProductByIdNotPalindrome_thenAccept() throws Exception {
		String uri = "/api/getProducts/180";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = mapFromJson(content, Product[].class);
		assertTrue(productlist.length == 1);
		
		Product product = productlist[0];
		assertNull(product.getDiscount());
		assertNull(product.getNormalPrice());
	}
	
	@Test
	void whenGetProductByTextPalindrome_thenAccept() throws Exception {
		String uri = "/api/getProducts/sdfds";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
		
		// If it works for one, it works for all
		Product product = productlist[0];
		assertNotNull(product.getDiscount());
		assertNotNull(product.getNormalPrice());
	}
	
	@Test
	void whenGetProductByTextNotPalindrome_thenAccept() throws Exception {
		String uri = "/api/getProducts/sdfd";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);

		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
		
		// If it works for one, it works for all
		Product product = productlist[0];
		assertNull(product.getDiscount());
		assertNull(product.getNormalPrice());
	}
	
	@Test
	void whenGetProductByIncompleteText_thenError() throws Exception {
		String uri = "/api/getProducts/sdf";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
		assertThatExceptionOfType(IncompleteSearchTextException.class);
	}
	
	@Test
	void whenGetUnknownUri_thenError() throws Exception {
		String uri = "/api/getProductos/sdf";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
		assertThatExceptionOfType(Throwable.class);
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
