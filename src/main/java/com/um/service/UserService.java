package com.um.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.um.models.*;
import com.um.util.*;
import com.um.repositories.*;

import java.util.List;

//Esto ya no esta haciendo nada paso a mejor vida
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private JWTUtil jwtUtil;
    
    public ResponseUtil validateUserHost(String token){
        try{
            User user = (User ) userRepository.getOne(User.class, Long.valueOf(jwtUtil.getKey(token)));
            if (user.getHost() != true) {
                return new ResponseUtil("Unauthorized", false, 401, null);
            }
            return new ResponseUtil("Rentas get successffuly", true, 200, (List<Object>) rentalRepository.getAll(Rental.class));
            
        }catch(Exception e){
            return new ResponseUtil("Missing Authorization Header", false, 401, null);
        }

    }

    // Aca tratar de hacer un decorator para validad que se ejecute una funcion solo si el usuario es host

}
