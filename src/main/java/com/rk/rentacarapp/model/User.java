package com.rk.rentacarapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotNull(message = "Please fill in your first name!")
    private String name;

    @NotNull(message = "Please fill in your last name!")
    private String surname;

    @Column(unique = true)
    @NotNull(message = "Please fill in your email!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotNull(message = "Please fill in your password")
    //@Size(min=6, max = 100, message = "Password length must be at least 6 characters long, 100 characters is the maximum")
    private String password;

    private Timestamp lastPasswordResetDate;

    private boolean accountExpired;
    private boolean accountLocked;
    private boolean accountEnabled;

    @OneToOne
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "authority_authorityId", referencedColumnName = "authorityId"))
    private Set<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountEnabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

}
