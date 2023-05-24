package edu.javeriana.users.control;

import edu.javeriana.users.entity.User;
import edu.javeriana.users.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Método de ejemplo que retorna un saludo.
     *
     * @return Saludo "Hola".
     */
    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    /**
     * Obtiene la lista de todos los usuarios.
     *
     * @return Respuesta HTTP con la lista de usuarios en el cuerpo de la respuesta.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<List<User>> list(){
        List<User> list = userService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario a obtener.
     * @return Respuesta HTTP con el usuario en el cuerpo de la respuesta.
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        if(!userService.existById(id)){
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        }
        User user = userService.getOneById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Obtiene un usuario por su email.
     *
     * @param email Email del usuario a obtener.
     * @return Respuesta HTTP con el usuario en el cuerpo de la respuesta.
     */
    @GetMapping("/detailname/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable("email") String email){
        if(!userService.existsByEmail(email)){
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        }
        User user = userService.getOneByEmail(email);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Realiza el inicio de sesión de un usuario.
     *
     * @param email    Email del usuario.
     * @param password Contraseña del usuario.
     * @return Respuesta HTTP indicando el resultado del inicio de sesión.
     */
    @PostMapping("/login/{email}&{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password){
        if(!userService.existsByEmail(email)){
            return new ResponseEntity("No existe un correo asociado", HttpStatus.BAD_REQUEST);
        }
        User user = userService.getOneByEmail(email);
        if(!user.getPassword().equals(password)){
            return new ResponseEntity("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity("Acceso concedido", HttpStatus.OK);
    }

    /**
     * Verifica si un usuario, dado su correo electrónico, es administrador.
     *
     * @param email Correo electrónico del usuario.
     * @return Respuesta HTTP indicando si el usuario es administrador o no.
     */
    @GetMapping("/checkAdmin/{email}")
    public ResponseEntity<?> checkAdmin(@PathVariable("email") String email) {
        if (!userService.existsByEmail(email)) {
            return new ResponseEntity("No existe un correo asociado", HttpStatus.UNAUTHORIZED);
        }

        User user = userService.getOneByEmail(email);
        if(!user.isAdmin()){
            return new ResponseEntity("El usuario NO es administador", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity("Administador", HttpStatus.OK);

    }

    /**
     * Crea un nuevo usuario.
     *
     * @param user Datos del usuario a crear.
     * @return Respuesta HTTP indicando el resultado de la creación del usuario.
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){
        if(StringUtils.isBlank(user.getEmail()))
            return new ResponseEntity("Se requiere el email", HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(user.getEmail()))
            return new ResponseEntity("El usuario ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        User userTmp = new User(user.getName(), user.getEmail(), user.getPassword(), user.getToken(), user.getBirthday(), user.getId_city(), user.isAdmin(), user.isActive());
        try{
            userService.save(userTmp);
            return new ResponseEntity("Usuario guardado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id   ID del usuario a actualizar.
     * @param user Datos del usuario actualizado.
     * @return Respuesta HTTP indicando el resultado de la actualización del usuario.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody User user){
        if(!userService.existById(id))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        if(userService.existsByEmail(user.getEmail()) && userService.getOneByEmail(user.getEmail()).getId() != id)
            return new ResponseEntity("El usuario ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(user.getEmail()))
            return new ResponseEntity("Se requiere el correo", HttpStatus.BAD_REQUEST);

        User userTmp = userService.getOneById(id);
        userTmp.setName(user.getName());
        userTmp.setEmail(user.getEmail());
        userTmp.setPassword(user.getPassword());
        userTmp.setToken(user.getToken());
        userTmp.setBirthday(user.getBirthday());
        userTmp.setId_city(user.getId_city());
        userTmp.setAdmin(user.isAdmin());
        userTmp.setActive(user.isActive());
        try{
            userService.save(userTmp);
            return new ResponseEntity("Usuario guardado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un usuario existente.
     *
     * @param id ID del usuario a eliminar.
     * @return Respuesta HTTP indicando el resultado de la eliminación del usuario.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!userService.existById(id))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        try{
            User userTmp = userService.getOneById(id);
            userTmp.setActive(false);
            userService.save(userTmp);
            return new ResponseEntity("Usuario eliminado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene una lista paginada de usuarios.
     *
     * @param page      Número de página.
     * @param size      Tamaño de la página.
     * @param order     Campo utilizado para ordenar la lista.
     * @param ascendent Indica si el orden es ascendente (true) o descendente (false).
     * @return Respuesta HTTP con la lista de usuarios paginada en el cuerpo de la respuesta.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/listPaged")
    public ResponseEntity<Page<User>> searchPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean ascendent
    ){
        Page<User> usersListPaged = userService.findAllPaged(PageRequest.of(page, size, Sort.by(order)));

        if(!ascendent){
            usersListPaged = userService.findAllPaged(PageRequest.of(page, size, Sort.by(order).descending()));
        }

        return new ResponseEntity<Page<User>>(usersListPaged, HttpStatus.OK);
    }
}
