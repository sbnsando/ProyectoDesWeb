package edu.javeriana.users.repository;

import edu.javeriana.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de los usuarios en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email la dirección de correo electrónico del usuario a buscar
     * @return el usuario encontrado o null si no existe
     */
    User findByEmail(String email);

    /**
     * Verifica si existe un usuario con la dirección de correo electrónico especificada.
     *
     * @param email la dirección de correo electrónico a verificar
     * @return true si existe un usuario con la dirección de correo electrónico, false en caso contrario
     */
    boolean existsByEmail(String email);
}
