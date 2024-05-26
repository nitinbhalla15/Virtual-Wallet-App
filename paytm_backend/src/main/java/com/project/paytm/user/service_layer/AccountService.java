package com.project.paytm.user.service_layer;


import com.project.paytm.db_entities.Accounts;
import com.project.paytm.db_entities.UserEntity;
import com.project.paytm.db_repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private UserRepository usrRepo;

    public Map<String,String> fetchAmountDetails(String username){
        UserEntity usrDetails = usrRepo.findByEmail(username).orElse(null);
        Accounts accInfo = usrDetails.getAccounts();
        Map<String,String> response = new HashMap<>();
        response.put("message","SUCCESS");
        response.put("data",accInfo.getAmount().toString());
        return response;
    }

    @Transactional
    public Map<String,String> transferMoney(String toUser,Long amount,String fromUser) throws Exception {
        UserEntity toUsrDetails = usrRepo.findByEmail(toUser).orElse(null);
        UserEntity fromUserDetails = usrRepo.findByEmail(fromUser).orElse(null);
        Map<String,String> response = new HashMap<>();
        long balance = fromUserDetails.getAccounts().getAmount();
        if(toUsrDetails!=null && balance>=amount){
            //send money only if the to user is valid and you have the money in your bank account
            toUsrDetails.getAccounts().setAmount(toUsrDetails.getAccounts().getAmount()+amount);
//            long infinityDiv = 2/0;
            fromUserDetails.getAccounts().setAmount(fromUserDetails.getAccounts().getAmount()-amount);
            response.put("message","SUCCESS");
            response.put("data","TRANSACTION SUCCESSFULL");
        }else{
            response.put("message","FAILURE");
            response.put("data","TRANSACTION FAILED !!");
        }
        return response;
    }
}
