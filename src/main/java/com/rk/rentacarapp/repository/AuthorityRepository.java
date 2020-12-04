package com.rk.rentacarapp.repository;

import com.rk.rentacarapp.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
