package com.rk.rentacarapp.repository;

import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByManufacturerContains(String s);

    Vehicle findByRegPlate(String regPlate);

    List<Vehicle> findAllByVehicleType(VehicleType vehicleType);

    List<Vehicle> findTop3ByVehicleType(VehicleType vehicleType);
}
