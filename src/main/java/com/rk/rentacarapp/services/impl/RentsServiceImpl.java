package com.rk.rentacarapp.services.impl;

import com.rk.rentacarapp.model.Rent;
import com.rk.rentacarapp.model.RentState;
import com.rk.rentacarapp.model.User;
import com.rk.rentacarapp.model.Vehicle;
import com.rk.rentacarapp.repository.RentRepository;
import com.rk.rentacarapp.services.RentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentsServiceImpl implements RentsService {
    @Autowired
    RentRepository rentRepository;

    @Override
    public String requestVehicleRent(Vehicle vehicle, User user, LocalDate startDay, LocalDate endDay) {

        //First some checks...

        //The start date must be today or a day 'in the future'
        if(startDay.compareTo(LocalDate.now()) < 0){
            //Start date is not valid
            return "You selected a start date in the past!";
        }

        //We check the same for the end date
        if(endDay.compareTo(LocalDate.now()) < 0){
            //End date is not valid
            return "You selected an end date in the past!";
        }

        //Now we check if the start date is >= than the end date
        if(startDay.compareTo(endDay) < 0){
            //End date is not valid it is before the start date
            return "You selected an end date before the start date";
        }


        //Everything checks-out we now proceed to see if the vehicle is free for the selected time

        List<Rent> rents = rentRepository.getAllByVehicleAndStartDateGreaterThanEqualAndEndDateLessThanEqual(vehicle, startDay, endDay);

        if(rents.isEmpty()){  //There are no reservations in the selected date range, we can now 'place' our reservation

            //Build
            Rent newRent = Rent.builder()
                               .rentState(RentState.PENDING_DECISION)
                               .startDate(startDay)
                               .endDate(endDay)
                               //.user(user)
                               .vehicle(vehicle)
                               .build();

            //And save
            rentRepository.save(newRent);

            //And return message
            return "Rent successfully registered, a company employee will contact you shortly";

        }else{
            //There are already some reservations in the selected date range
            return "Rent cannot be made in the selected range, an overlap with another reservation exists";
        }
    }
}
