package com.rk.rentacarapp.repository;

import com.rk.rentacarapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByNameContainsAndSurnameContains(String name, String surname);
}
