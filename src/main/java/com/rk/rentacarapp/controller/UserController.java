package com.rk.rentacarapp.controller;

import com.rk.rentacarapp.model.Authority;
import com.rk.rentacarapp.model.User;
import com.rk.rentacarapp.repository.AuthorityRepository;
import com.rk.rentacarapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository ar;

    @GetMapping("/findByEmail")
    @PreAuthorize("hasRole('ROLE_STANDARD-USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email){
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/findAllUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/findUsersByName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> findUsersByNameAndSurname(@RequestParam String name, @RequestParam String surname){
        return new ResponseEntity<>(userService.findUsersByNameAndSurname(name, surname), HttpStatus.OK);
    }


    @PostMapping(value = "/newAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<String>> addNewAdmin(@RequestBody User user){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        List<String> result = new ArrayList<>();

        if(!violations.isEmpty()){
            for(ConstraintViolation<User> violation : violations){
                result.add("\"" + violation.getMessage() + "\n");
            }

            return new ResponseEntity<>(result, HttpStatus.OK); // Some other HTTP status should probably be used
        }
        else{

            user.setAccountEnabled(true);
            user.setAccountExpired(false);
            user.setAccountLocked(false);
            user.setAuthorities(new HashSet<>());

            Authority newUserAuthority = new Authority();
            newUserAuthority.setAuthority("ROLE_ADMIN");
            user.addAuthority(newUserAuthority);
            ar.save(newUserAuthority);

            user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));

            userService.registerUser(user);

            result.add("Successfully registered");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

}
