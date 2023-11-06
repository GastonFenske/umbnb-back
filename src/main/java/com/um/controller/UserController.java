package com.um.controller;

import com.um.models.User;
import com.um.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.um.auth.HostRequired;
import com.um.auth.UserAuth;
import com.um.util.ResponseUtil;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuth userAuth;


    @RequestMapping("/users")
    public List<Object> getUsers(){
        return (List<Object>) userRepository.getAll(User.class);
    }

    @PostMapping("/users")
    public ResponseUtil createUser(@RequestBody User user){
        return userAuth.verifyEmailDuplicated(user);
    }

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return (User) userRepository.getOne(User.class, id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        User user = (User) userRepository.getOne(User.class, id);
        userRepository.deleteOne(user);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        User current_user = (User) userRepository.getOne(User.class, id);
        current_user.setAge(user.getAge());
        current_user.setName(user.getName());
        current_user.setPassword(user.getPassword());
        current_user.setSex(user.getSex());
        current_user.setHost(user.getHost());
        current_user.setLastname(user.getLastname());
        current_user.setPhoneNumber(user.getPhoneNumber());
        current_user.setProvince(user.getProvince());
        current_user.setEmail(user.getEmail());

        userRepository.update(current_user);
    }
}

