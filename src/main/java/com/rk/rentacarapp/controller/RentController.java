package com.rk.rentacarapp.controller;

import com.rk.rentacarapp.model.User;
import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.services.RentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/rents", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentController {

    @Autowired
    RentsService rentsService;

    @PostMapping("/requestRent")
    @PreAuthorize("hasRole('ROLE_STANDARD-USER')")
    ResponseEntity<String> requestRent(@RequestBody User user, @RequestBody Vehicle vehicle, @RequestBody LocalDate startDate, @RequestBody LocalDate endDate){
        return new ResponseEntity<>(rentsService.requestVehicleRent(vehicle, user, startDate, endDate), HttpStatus.OK);
    }
}
