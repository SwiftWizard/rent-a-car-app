package com.rk.rentacarapp.services.impl;

import com.rk.rentacarapp.model.User;
import com.rk.rentacarapp.repository.UserRepository;
import com.rk.rentacarapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository ur;

    @Override
    public User findByEmail(String email) {
        return ur.findByEmail(email);
    }

    @Override
    public void registerUser(User user) {
        ur.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return ur.findAll();
    }

    @Override
    public List<User> findUsersByNameAndSurname(String name, String surname) {
        return ur.findAllByNameContainsAndSurnameContains(name, surname);
    }


}
