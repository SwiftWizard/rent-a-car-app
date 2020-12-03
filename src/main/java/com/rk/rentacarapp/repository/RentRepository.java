package com.rk.rentacarapp.repository;

import com.rk.rentacarapp.model.Rent;
import com.rk.rentacarapp.model.Vehicle;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    //Bože blagoslovi razvojni tim koji nam je podario ovaj moćni Spring Data JPA
    //God bless the development team that made this mighty Spring Data JPA

    List<Rent> getAllByVehicleAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Vehicle v, LocalDate startDate, LocalDate endDate);
}
