package com.rk.rentacarapp.services;

import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.model.VehicleType;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAllVehicles();

    Vehicle findVehicleById(Long id);

    Vehicle findVehicleByRegPlate(String regPlate);

    List<Vehicle> findAllVehiclesByType(VehicleType vehicleType);

    List<Vehicle> findTop3(VehicleType vehicleType);

    Vehicle addNewVehicle(Vehicle vehicle);

    Vehicle patchVehicle(Long id, Vehicle vehicle);

    boolean deleteVehicle(Long id);

}
