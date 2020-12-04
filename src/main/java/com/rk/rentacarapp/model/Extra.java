package com.rk.rentacarapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Extra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long extraId;

    private String name;
    private String description;
    private int totalAmount;
    private int availableAmount;

    @ManyToMany(mappedBy = "extras", fetch = FetchType.EAGER)
    private Set<Rent> rents;

}
