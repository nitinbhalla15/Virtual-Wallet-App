package com.project.paytm.user.controller_layer;

import com.project.paytm.db_entities.UserEntity;
import com.project.paytm.user.service_layer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService usrService;

    @PutMapping(value = "/updateInfo")
    public ResponseEntity<Object> updateUserInfo(
            @RequestBody UserEntity updatedUserDetails){
        return ResponseEntity.ok().body(usrService.updateUserInfo(updatedUserDetails));
    }

    @GetMapping(value = "/findUser/{username}")
    public ResponseEntity<Object> findUsers(@PathVariable("username") String username){
        return ResponseEntity.ok().body(usrService.findAllUsers(username));
    }

}
