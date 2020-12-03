package com.rk.rentacarapp.controller;

import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.model.VehicleType;
import com.rk.rentacarapp.services.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Vehicle>> allVehicles(){
        return new ResponseEntity<>(vehicleService.findAllVehicles(), HttpStatus.OK);
    }

    @GetMapping(value = "/{vehicleType}")
    public ResponseEntity<List<Vehicle>> allVehiclesOfType(@PathVariable VehicleType vehicleType){
        return new ResponseEntity<>(vehicleService.findAllVehiclesByType(vehicleType), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD-USER')")
    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable Long id){
        return new ResponseEntity<>(vehicleService.findVehicleById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/vehicle/{id}")
    public ResponseEntity<Vehicle> patchVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.patchVehicle(id, vehicle), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
        return new ResponseEntity<>(vehicleService.deleteVehicle(id), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD-USER')")
    @GetMapping(value = "/findVehicle/{regPlate}")
    public Vehicle findVehicleByRegPlate(@PathVariable String regPlate){
        return vehicleService.findVehicleByRegPlate(regPlate);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/newVehicle")
    public ResponseEntity<Vehicle> createNewVehicle(@RequestBody Vehicle vehicle){
        return new ResponseEntity<Vehicle> (vehicleService.addNewVehicle(vehicle), HttpStatus.CREATED);
    }

}
