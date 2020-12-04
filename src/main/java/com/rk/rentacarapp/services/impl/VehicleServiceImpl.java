package com.rk.rentacarapp.services.impl;

import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.model.VehicleType;
import com.rk.rentacarapp.repository.VehicleRepository;
import com.rk.rentacarapp.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> findAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle findVehicleById(Long id){
        return vehicleRepository.findById(id).get();
    }

    public Vehicle findVehicleByRegPlate(String regPlate){
        return vehicleRepository.findByRegPlate(regPlate);
    }

    public List<Vehicle> findAllVehiclesByType(VehicleType vehicleType){
        return vehicleRepository.findAllByVehicleType(vehicleType);
    }

    @Override
    public List<Vehicle> findTop3(VehicleType vehicleType) {
        return vehicleRepository.findTop3ByVehicleType(vehicleType);
    }

    @Override
    public Vehicle addNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle patchVehicle(Long id, Vehicle vehicle) {
        Vehicle v = vehicleRepository.findById(id).get();

        if(vehicle.getPhotoA() != null && !vehicle.getPhotoA().isEmpty()){
            v.setPhotoA(vehicle.getPhotoA());;
        }

        if(vehicle.getPhotoB() != null && !vehicle.getPhotoB().isEmpty()){
            v.setPhotoB(vehicle.getPhotoB());;
        }

        if(vehicle.getPhotoC() != null && !vehicle.getPhotoC().isEmpty()){
            v.setPhotoB(vehicle.getPhotoC());;
        }

        if(vehicle.getRegPlate() != null && !vehicle.getRegPlate().isEmpty()){
            v.setRegPlate(vehicle.getRegPlate());
        }

        if(vehicle.getRentPrice() != null){
            v.setRentPrice(vehicle.getRentPrice());
        }

        if(vehicle.getManufacturer() != null && !vehicle.getManufacturer().isEmpty()){
            v.setManufacturer(vehicle.getManufacturer());
        }

        if(vehicle.getModel() != null && !vehicle.getModel().isEmpty()){
            v.setModel(vehicle.getModel());
        }

        if(vehicle.getVehicleType() != null){
            v.setVehicleType(vehicle.getVehicleType());
        }

        if(vehicle.getManufactureDate() != null){
            v.setManufactureDate(vehicle.getManufactureDate());
        }

        return vehicleRepository.save(v);
    }

    @Override
    public boolean deleteVehicle(Long id) {
        Vehicle v = vehicleRepository.findById(id).get();

        if(v != null){
            vehicleRepository.delete(v);
            return true;
        }

        return false;

    }
}
