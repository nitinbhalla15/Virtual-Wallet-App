package com.project.paytm.authentication.auth_service;


import com.project.paytm.authentication.filter.JWTFilterService;
import com.project.paytm.authentication.login_entity.LoginRequest;
import com.project.paytm.db_entities.Accounts;
import com.project.paytm.db_entities.UserEntity;
import com.project.paytm.db_repositories.AccountRepository;
import com.project.paytm.db_repositories.UserRepository;
import com.project.paytm.dtos.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {


    @Autowired
    private UserRepository usrRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTFilterService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AccountRepository accRepo;

    public Map<String,Object> registerUser(UserEntity userDetails){
        Map<String,Object> responseMap = new HashMap<>();
        try{
            Accounts accDetails = new Accounts();
            accDetails.setAmount((long)(1+(Math.random()*10000)));
            UserEntity usrToBeAdded = UserEntity.builder()
                    .email(userDetails.getEmail())
                    .firstName(userDetails.getFirstName())
                    .lastName(userDetails.getLastName())
                    .password(passwordEncoder.encode(userDetails.getPassword()))
                    .accounts(accDetails)
                    .build();

            UserEntity savedUSer = usrRepo.save(usrToBeAdded);
            UserResponse userResponse = UserResponse.builder().
                    username(savedUSer.getUsername())
                    .userInitial(savedUSer.getUsername().substring(0,1))
                    .userBalance(accDetails.getAmount())
                    .build();
            String jwtToken =  jwtService.generateToken(usrToBeAdded);
            responseMap.put("message","SUCCESS");
            responseMap.put("token",jwtToken);
            responseMap.put("user_info",userResponse);
        }catch (Exception e){
            responseMap.put("message","FAILURE");
        }
        return responseMap;
    }

    public Map<String,Object> signIn(LoginRequest loginDetails){
        Map<String,Object> responseMap = new HashMap<>();
        try{
            authManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDetails.getEmail(),
                    loginDetails.getPassword()
            ));
            UserEntity userDetails = usrRepo.findByEmail(loginDetails.getEmail()).orElse(null);
            UserResponse userResponse = UserResponse.builder().
                    username(userDetails.getUsername())
                    .userInitial(userDetails.getUsername().substring(0,1))
                    .userBalance(userDetails.getAccounts().getAmount())
                    .build();
            String jwtToken =  jwtService.generateToken(userDetails);
            responseMap.put("message","SUCCESS");
            responseMap.put("token",jwtToken);
            responseMap.put("user_info",userResponse);
        }catch (Exception e){
            responseMap.put("message","FAILURE");
        }
        return responseMap;
    }


}
