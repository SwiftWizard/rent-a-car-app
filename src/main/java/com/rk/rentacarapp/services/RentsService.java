package com.rk.rentacarapp.services;

import com.rk.rentacarapp.model.User;
import com.rk.rentacarapp.model.Vehicle;

import java.time.LocalDate;

public interface RentsService {

    String requestVehicleRent(Vehicle vehicle, User user, LocalDate startDay, LocalDate endDay);
}
