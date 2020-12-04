package com.rk.rentacarapp.services;

import com.rk.rentacarapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    void registerUser(User user);

    List<User> findAllUsers();

    List<User> findUsersByNameAndSurname(String name, String surname);

}
