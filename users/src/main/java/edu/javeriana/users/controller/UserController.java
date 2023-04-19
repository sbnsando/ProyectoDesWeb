package edu.javeriana.users.controller;

import edu.javeriana.users.entity.User;
import edu.javeriana.users.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> list(){
        List<User> list = userService.list();
        if(list.isEmpty()){
            return new ResponseEntity("No users registered in the system.", HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        if(!userService.existById(id)){
            return new ResponseEntity("Not exist", HttpStatus.NOT_FOUND);
        }
        User user = userService.getOneById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @GetMapping("/detailname/{name}")
    public ResponseEntity<User> getByName(@PathVariable("name") String name){
        if(!userService.existByName(name)){
            return new ResponseEntity("Not exist", HttpStatus.NOT_FOUND);
        }
        User user = userService.getOneByName(name);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){
        if(StringUtils.isBlank(user.getName()))
            return new ResponseEntity("Name is required",HttpStatus.BAD_REQUEST);
        if(userService.findByEmail(user.getEmail()))
            return new ResponseEntity("User exist in data base",HttpStatus.BAD_REQUEST);

        User temp = new User();
        temp.setName(user.getName());
        temp.setEmail(user.getEmail());
        temp.setPassword(passwordEncoder.encode(user.getPassword()));
        temp.setLogin(user.getLogin());
        temp.setToken(user.getToken());
        temp.setBirthdate(user.getBirthdate());
        temp.setId_city(user.getId_city());
        temp.setAdmin(user.isAdmin());
        temp.setActive(user.isActive());
        try{
            userService.save(temp);
            return new ResponseEntity("User saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody User user){

        if(!userService.existById(id))
            return new ResponseEntity("not exist", HttpStatus.NOT_FOUND);
        if(userService.existByName(user.getName()) && userService.getOneByName(user.getName()).getId() != id)
            return new ResponseEntity("user exist in database", HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(user.getName()))
            return new ResponseEntity("Name is required",HttpStatus.BAD_REQUEST);

        User temp = userService.getOneById(id);
        temp.setName(user.getName());
        temp.setEmail(user.getEmail());
        temp.setPassword(passwordEncoder.encode(user.getPassword()));
        temp.setLogin(user.getLogin());
        temp.setToken(user.getToken());
        temp.setBirthdate(user.getBirthdate());
        temp.setId_city(user.getId_city());
        temp.setAdmin(user.isAdmin());
        temp.setActive(user.isActive());
        try{
            userService.save(temp);
            return new ResponseEntity("User saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!userService.existById(id))
            return new ResponseEntity("not exist", HttpStatus.NOT_FOUND);
        User user = userService.getOneById(id);
        user.setActive(false);
        userService.save(user);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }
}
