package edu.javeriana.users.service;

import edu.javeriana.users.entity.User;
import edu.javeriana.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para operaciones relacionadas con usuarios.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Obtiene la lista de todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    public List<User> list(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return Usuario correspondiente al ID, o null si no se encuentra.
     */
    public User getOneById(int id){
        User user = null;
        user = userRepository.findById(id).get();
        return user;
    }

    /**
     * Obtiene un usuario por su dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico del usuario.
     * @return Usuario correspondiente al correo electrónico, o null si no se encuentra.
     */
    public User getOneByEmail(String email){
        User user = null;
        user = userRepository.findByEmail(email);
        return user;
    }

    /**
     * Guarda un usuario.
     *
     * @param user Usuario a guardar.
     */
    public void save(User user){
        userRepository.save(user);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario a eliminar.
     */
    public void delete(int id){
        userRepository.deleteById(id);
    }

    /**
     * Verifica si un usuario existe por su ID.
     *
     * @param id ID del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existById(int id){
        return userRepository.existsById(id);
    }

    /**
     * Verifica si un usuario existe por su dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    /**
     * Obtiene una página de usuarios.
     *
     * @param pageable Configuración de paginación.
     * @return Página de usuarios.
     */
    public Page<User> findAllPaged(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
