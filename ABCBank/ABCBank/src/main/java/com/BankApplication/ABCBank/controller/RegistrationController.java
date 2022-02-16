package com.BankApplication.ABCBank.controller;

import com.BankApplication.ABCBank.model.RequestRegisterModel;
import com.BankApplication.ABCBank.model.ResponseSuccessFailModel;
import com.BankApplication.ABCBank.service.RegistrationCheckerService;
import com.BankApplication.ABCBank.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    RegistrationCheckerService registrationCheckerService;

    @Autowired
    RegistrationService registrationService;

    //registerUser
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RequestRegisterModel request){

        ResponseSuccessFailModel response = new ResponseSuccessFailModel();
        response.setStatus(false);

        log.info(request.getNRIC());

        try {
            if (registrationCheckerService.nameCheck(request.getName()) ||
                    registrationCheckerService.nricCheck(request.getNRIC())) {
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try{
            registrationService.saveToH2(request);
        }catch(Exception e){
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
        response.setStatus(true);
        return new ResponseEntity(response, HttpStatus.OK);
//        return new ResponseEntity(HttpStatus.OK);
    }
    //getUsers <-- admin
    //getSpecificUser <-- admin

}
