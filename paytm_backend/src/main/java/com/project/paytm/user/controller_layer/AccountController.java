package com.project.paytm.user.controller_layer;

import com.project.paytm.user.service_layer.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {



    @Autowired
    private AccountService accService;

    @GetMapping(value = "/getBalance/{username}")
    public ResponseEntity<Object> fetchBalance(@PathVariable("username") String userEmail){
        return ResponseEntity.ok().body(accService.fetchAmountDetails(userEmail));
    }

    @PostMapping(value = "/transfer/{from}/{to}/{amount}")
    public ResponseEntity<Object> transferAmount(@PathVariable("to") String toUserEmail,
                                                 @PathVariable("amount") Long money,
                                                 @PathVariable("from") String fromUser) throws Exception {
        return ResponseEntity.ok().body(accService.transferMoney(toUserEmail,money,fromUser));
    }
}
