package com.project.paytm.user.service_layer;

import com.project.paytm.db_entities.UserEntity;
import com.project.paytm.db_repositories.UserRepository;
import com.project.paytm.dtos.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository usrRepo;

    public Map<String,String> updateUserInfo(UserEntity usrDetails){
        Map<String,String> responseMap = new HashMap<>();
        UserEntity userFromDb = usrRepo.findByEmail(usrDetails.getUsername()).orElse(null);
        if(userFromDb!=null){
            if(usrDetails.getPassword()!=null){
                userFromDb.setPassword(usrDetails.getPassword());
            }
            if(usrDetails.getFirstName()!=null){
                userFromDb.setFirstName(usrDetails.getFirstName());
            }
            if(usrDetails.getLastName()!=null){
                userFromDb.setLastName(usrDetails.getLastName());
            }
            usrRepo.save(userFromDb);
            responseMap.put("message","SUCCESS");
            responseMap.put("data","User details has been successfully updated");
        }else{
            responseMap.put("message","FAILURE");
            responseMap.put("data","USER NOT FOUND !!");
        }
        return responseMap;
    }

    public Map<String,Object> findAllUsers(String username){
        List<UserEntity> userDetails = usrRepo.findByUsername(username).orElse(null);
        List<UserResponse> userRespone = userDetails.stream().map((user)->{
            UserResponse usrRes = UserResponse.builder().username(user.getUsername())
                    .userInitial(user.getFirstName().substring(0,1))
                    .userBalance(user.getAccounts().getAmount()).build();
            return usrRes;
        }).collect(Collectors.toList());
        System.out.println("user response: "+userRespone);
        Map<String,Object> resposne = new HashMap<>();
        if(userDetails!=null && userDetails.size()>0){
            resposne.put("message","SUCCESS");
            resposne.put("data",userRespone);
            System.out.println("Response sent back");
        }else{
            resposne.put("message","FAILURE");
            resposne.put("data",List.of());
        }
        return resposne;
    }

}
