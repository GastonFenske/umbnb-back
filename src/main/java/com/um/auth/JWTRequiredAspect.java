package com.um.auth;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.um.models.User;
import com.um.repositories.UserRepository;
import com.um.util.*;


@Component
@Aspect
public class JWTRequiredAspect {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Around("@annotation(JWTRequired)")
    public Object wapper(ProceedingJoinPoint joinPoint) throws Throwable {
        // System.out.println("Entra acaaa");
        // return null;
        try{
            //aca podemos tener error cuando le pasamos argumento por parametro en la url
            // Object[] args = joinPoint.getArgs();
            String token = joinPoint.getArgs()[0].toString();
            if(token != null){
                System.out.println("Entra aca" + token);
                return new ResponseUtil("Resource ger succesfully", true, 200, joinPoint.proceed());
            }
            return new ResponseUtil("Token wrong", true, 401, null);

        }catch (Exception e){
            System.out.println("nose si entra aca");
            return new ResponseUtil("Login required", true, 401, null);
        }
        // return joinPoint.proceed();
        // return new ResponseUtil("Resource ger succesfully", true, 200, joinPoint.proceed());
    }
}
