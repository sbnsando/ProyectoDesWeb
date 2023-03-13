package edu.javeriana.users.controller;

import edu.javeriana.users.dto.Message;
import edu.javeriana.users.dto.UserDto;
import edu.javeriana.users.entity.User;
import edu.javeriana.users.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> list(){
        List<User> list = userService.list();
        if(list.isEmpty()){
            return new ResponseEntity(new Message("No users registered in the system."), HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        if(!userService.existById(id)){
            return new ResponseEntity(new Message("Not exist"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getOneById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @GetMapping("/detailname/{name}")
    public ResponseEntity<User> getByName(@PathVariable("name") String name){
        if(!userService.existByName(name)){
            return new ResponseEntity(new Message("Not exist"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getOneByName(name);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        if(StringUtils.isBlank(userDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);
        if(userService.findByIdentityNumb(userDto.getIdentityNumb()))
            return new ResponseEntity(new Message("User exist in data base"),HttpStatus.BAD_REQUEST);

        User user = new User(userDto.getIdentityNumb(),userDto.getName(),userDto.getBirthday(),userDto.getIdCity(),userDto.isAdmin());
        try{
            userService.save(user);
            return new ResponseEntity(new Message("User saved"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody UserDto userDto){

        if(!userService.existById(id))
            return new ResponseEntity(new Message("not exist"), HttpStatus.NOT_FOUND);
        if(userService.existByName(userDto.getName()) && userService.getOneByName(userDto.getName()).getId() != id)
            return new ResponseEntity(new Message("user exist in database"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(userDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);

        User user = userService.getOneById(id);
        user.setIdentityNumb(userDto.getIdentityNumb());
        user.setName(userDto.getName());
        user.setBirthday(userDto.getBirthday());
        user.setIdCity(userDto.getIdCity());
        user.setAdmin(userDto.isAdmin());
        try{
            userService.save(user);
            return new ResponseEntity(new Message("User saved"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!userService.existById(id))
            return new ResponseEntity(new Message("not exist"), HttpStatus.NOT_FOUND);
        try{
            userService.delete(id);
            return new ResponseEntity(new Message("User deleted"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
