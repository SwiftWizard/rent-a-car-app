package com.rk.rentacarapp.repository;

import com.rk.rentacarapp.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
