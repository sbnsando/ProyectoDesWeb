package edu.javeriana.brands;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrandsApplicationTests {

	@Test
	void contextLoads() { }

	@Test
	void mainTest() {
		assertDoesNotThrow(() -> BrandsApplication.main(new String[]{}));
	}
}
