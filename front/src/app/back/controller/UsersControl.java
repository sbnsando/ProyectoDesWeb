package app.back.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.back.entities.User;

public class UsersControl {
    
    List<User> lUsers;

    public UsersControl(){
        lUsers = new ArrayList<User>();
        User us1 = new User("esteban@gmail.com", "12345", "Esteban");
        User us2 = new User("sebastian@gmail.com", "54321", "Sebastian");
        lUsers.add(us1);
        lUsers.add(us2);
    }

    public boolean existByEmail(String email){
        boolean exist = false;
        Iterator<User> iter = lUsers.iterator();
        while (iter.hasNext()){
            if(iter.next().getName().equals(email))
                exist = true;
        }
        return exist;
    }

}
