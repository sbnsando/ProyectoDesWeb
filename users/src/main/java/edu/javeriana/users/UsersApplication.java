package edu.javeriana.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para la aplicación Users.
 * Esta clase inicia la aplicación Spring Boot.
 */
@SpringBootApplication
public class UsersApplication {

	/**
	 * Método principal que inicia la aplicación Users.
	 *
	 * @param args los argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}
