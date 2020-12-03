package com.rk.rentacarapp.controller;

import com.rk.rentacarapp.model.AuthenticationRequest;
import com.rk.rentacarapp.model.AuthenticationResponse;
import com.rk.rentacarapp.model.Authority;
import com.rk.rentacarapp.model.User;
import com.rk.rentacarapp.repository.AuthorityRepository;
import com.rk.rentacarapp.services.UserService;
import com.rk.rentacarapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository ar;

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, @Qualifier("myUserDetailsService") UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));

        }catch (BadCredentialsException e) {
            //TODO: review this peace of code later, change of this peace of code may be needed or desired
            System.err.println("\n\n!!!!!!!!!!!!!!!!!!!!\nIncorrect username or password!\n!!!!!!!!!!!!!!!!!!!!\n\n");

            return new ResponseEntity<String>("Unauthorized, incorrect username or password!", HttpStatus.UNAUTHORIZED);

        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<List<String>> register(@RequestBody User user){
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

            user.addAuthority(newUserAuthority);

            ar.save(newUserAuthority);

            user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));

            try{
                userService.registerUser(user);
                result.add("Successfully registered");
            }catch (DataIntegrityViolationException ex){
                result.add("The entered email already exists in our system!");
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/findByEmail")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STANDARD-USER')")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email){
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }
}
