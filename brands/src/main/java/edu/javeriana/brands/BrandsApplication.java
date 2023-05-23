package edu.javeriana.brands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * La clase principal para la aplicación Brands.
 * Esta clase es responsable de inicializar y ejecutar la aplicación Spring Boot.
 */
@SpringBootApplication
public class BrandsApplication {

	/**
	 * El método principal que inicia la aplicación Brands.
	 *
	 * @param args Los argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BrandsApplication.class, args);
	}

}

