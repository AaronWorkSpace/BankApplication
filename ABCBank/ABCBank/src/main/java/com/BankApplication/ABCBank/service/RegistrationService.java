package com.BankApplication.ABCBank.service;

import com.BankApplication.ABCBank.model.RequestRegisterModel;
import com.BankApplication.ABCBank.model.UserModel;
import com.BankApplication.ABCBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    UserRepository userRepository;

    public boolean saveToH2(RequestRegisterModel request){
        try {
            UserModel userModel = UserModel.builder()
                    .name(request.getName())
                    .nric(request.getNric())
                    .streetAddr(request.getStreetAddr())
                    .blockAddr(request.getBlockAddr())
                    .unitAddr(request.getUnitAddr())
                    .postalCodeAddr(request.getPostalCodeAddr())
                    .phoneNumber(request.getPhoneNumber())
                    .depositAmount(request.getDepositAmount())
                    .build();

            userRepository.save(userModel);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
