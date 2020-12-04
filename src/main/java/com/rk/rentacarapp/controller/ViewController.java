package com.rk.rentacarapp.controller;

import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.model.VehicleType;
import com.rk.rentacarapp.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/view", produces = MediaType.APPLICATION_JSON_VALUE)
public class ViewController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles/vehicleTypes")
    public ResponseEntity<VehicleType[]> getVehicleTypes(){
        return new ResponseEntity<>(VehicleType.values(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/findByType/{vehicleType}")
    public ResponseEntity<List<Vehicle>> getVehiclesByType(@PathVariable(value = "vehicleType") VehicleType vehicleType){
        return new ResponseEntity<>(vehicleService.findAllVehiclesByType(vehicleType), HttpStatus.OK);
    }

    @GetMapping("/vehicles/top3")
    public ResponseEntity<List<Vehicle>> top3Vehicles(){
        return new ResponseEntity<>(vehicleService.findTop3(VehicleType.CARS), HttpStatus.OK);
    }

}
