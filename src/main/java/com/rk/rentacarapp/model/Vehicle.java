package com.rk.rentacarapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    private String registrationPlate;

    @Enumerated(value = EnumType.STRING)
    private VehicleType vehicleType;

    private String manufacturer;
    private String model;
    private LocalDate manufactureDate;

    //URLs to photos
    private String photoA;
    private String photoB;
    private String photoC;

    private Double rentPrice;

}
