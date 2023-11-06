package com.um.repositories;
import com.um.models.User;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@Repository
@Transactional
public class UserRepository implements BaseRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Object getOne(Object model, Long id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<Object> getAll(Object model){
        String query = String.format("FROM User");
        List<Object> users = entityManager.createQuery(query).getResultList();
        return users;
    }

    @Override
    public void create(Object model){
        User user = (User) model;
        entityManager.merge(user);
    }

    @Override
    public void deleteOne(Object model){
        User user = (User) model;
        entityManager.remove(user);
    }

    @Override
    public void update(Object model){
        User user = (User) model;
        entityManager.merge(user);
    }

    public Object getUserByEmail(User user){
        String query = "FROM User WHERE email = :email";
        try{
            return (Object) entityManager.createQuery(query).setParameter("email", user.getEmail()).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

}

