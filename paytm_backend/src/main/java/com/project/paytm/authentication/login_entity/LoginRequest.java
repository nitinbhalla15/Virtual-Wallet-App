package com.project.paytm.authentication.login_entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Email id should not be blank !!")
    @Email(message = "Invalid Email id !!")
    private String email;
    @NotBlank(message = "Password should not be blank !!")
    private String password;
}
