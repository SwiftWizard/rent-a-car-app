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
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentId;

    @Enumerated(EnumType.STRING)
    private RentState rentState;

    @ManyToMany()
    @JoinTable(name = "rent_extra",
            joinColumns = @JoinColumn(name = "rent_rentId"),
            inverseJoinColumns = @JoinColumn(name = "extra_extraId"))
    private Set<Extra> extras;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;

    @OneToOne
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;

}
