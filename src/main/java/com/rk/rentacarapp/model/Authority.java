package com.rk.rentacarapp.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorityId;

    private String authority = "STANDARD_USER";

    @Override
    public String getAuthority() {
        return authority;
    }
}
