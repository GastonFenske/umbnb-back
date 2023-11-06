package com.um.controller;

import com.um.auth.HostRequired;
import com.um.auth.JWTRequired;
import com.um.models.Rental;
import com.um.repositories.RentalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.um.util.ResponseUtil;

import java.util.List;

import com.um.service.*;

@RestController
public class RentalController {
    
    @Autowired
    private RentalRepository rentalRepository;

    @HostRequired
    @RequestMapping("/rentals")
    public Object getRentals(@RequestHeader("Authorization") String token){
        return (List<Object>) rentalRepository.getAll(Rental.class);
    }

    @PostMapping("/rentals")
    public void createRental(@RequestBody Rental rental){
        rentalRepository.create(rental);
    }

    @JWTRequired
    @RequestMapping("/rental/{id}")
    public Object getRental(@PathVariable Long id){
        return (Rental) rentalRepository.getOne(Rental.class, id);
    }

    @DeleteMapping("/rental/{id}")
    public void deleteRental(@PathVariable Long id){
        Rental rental = (Rental) rentalRepository.getOne(Rental.class, id);
        rentalRepository.deleteOne(rental);
    }

    @PutMapping("/rental/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Rental rental){
        Rental current_rental = (Rental) rentalRepository.getOne(Rental.class, id);
        current_rental.setDate(rental.getDate());
        current_rental.setPrice(rental.getPrice());
        current_rental.setClient(rental.getClient());
        current_rental.setHost(rental.getHost());
        current_rental.setHouse(rental.getHouse());


        rentalRepository.update(current_rental);
    }

}
