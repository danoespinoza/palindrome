package cl.danoespinoza.palindrome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PalindromeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PalindromeApplication.class, args);
	}

}
