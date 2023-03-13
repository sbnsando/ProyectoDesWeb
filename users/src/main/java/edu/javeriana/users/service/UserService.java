package edu.javeriana.users.service;

import edu.javeriana.users.entity.User;
import edu.javeriana.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> list(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;

    }

    public User getOneById(int id) {

        User user = null;
        user = userRepository.findById(id).get();

        return user;
    }

    public User getOneByIdentityNumb(int identityNumb){
        User user = null;
        user = userRepository.findByIdentityNumb(identityNumb);

        return user;
    }

    public boolean findByIdentityNumb(int identityNumb){
        User user = null;
        user = userRepository.findByIdentityNumb(identityNumb);

        return user==null ? false : true;
    }

    public User getOneByName(String name) {

        User user = null;
        user = userRepository.findByName(name);

        return user;
    }
    public void save(User User){

        userRepository.save(User);
    }

    public void delete(int id){

        userRepository.deleteById(id);
    }

    public boolean existById(int id){

        return userRepository.existsById(id);
    }

    public boolean existByName(String name){

        return userRepository.findByName(name) == null ? false : true;
    }
}
