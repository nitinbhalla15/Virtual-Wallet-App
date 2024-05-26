package com.project.paytm.db_entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_info")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "FirstName Field is mandatory !!")
    @Column(unique=false)
    private String firstName;
    @NotBlank(message = "LastName Field is mandatory !!")
    @Column(unique = false)
    private String lastName;
    @NotBlank(message = "Username/Email Field is mandatory !! ")
    @Email(message = "Invalid Email !!")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Password field is mandatory !!")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_id")
    private Accounts accounts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
