package edu.javeriana.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ToolsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainTest() {
		assertDoesNotThrow(() -> ToolsApplication.main(new String[]{}));
	}

}
