package com.rk.rentacarapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;
    
    private String regPlate;

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


    /*
      @JsonIgnore fixes the following problem:
       JSON: Infinite recursion (StackOverflowError); nested exception is com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError) (through reference chain: java.util.ArrayList[0]->com.rk.rentacarapp.model.Vehicle["rents"])]
     */
    @JsonIgnore
    @OneToMany(mappedBy="vehicle", targetEntity = Rent.class)
    private Set<Rent> rents;

}
