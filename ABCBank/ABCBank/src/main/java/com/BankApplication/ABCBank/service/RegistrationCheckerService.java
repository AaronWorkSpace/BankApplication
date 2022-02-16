package com.BankApplication.ABCBank.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.BankApplication.ABCBank.model.UserModel;
import com.BankApplication.ABCBank.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationCheckerService {
    //condition:
    //check name no digit
    public boolean nameCheck(String name){
        return name.matches("[a-zA-Z]+");
    }

    //check NRIC
    public boolean nricCheck(String nric){
        char[] st = "JZIHGFEDCBA".toCharArray();
        char[] fg = "XWUTRQPNMLK".toCharArray();

        //check if nric length is 9
        if(nric.length() != 9){
            return false;
        }

        nric = nric.toUpperCase();

        char[] icArray = new char[9];

        for (int i = 0; i < 9; i++) {
            icArray[i] = nric.charAt(i);
        }


        if(icArray[0] != 'S' && icArray[0] != 'T' && icArray[0] != 'G'){
            log.debug("ch: {}", icArray[0]);
            return false;
        }

        //check if there is 7 digits
        for(int i = 1; i < nric.length() - 1; i++){
            if(!Character.isDigit(icArray[i])){
                log.debug("One of the digit is letter / symbol");
                return false;
            }
        }

        //multiply by weight 2, 7, 6, 5, 4, 3, 2
        int sum = (Integer.parseInt(String.valueOf(icArray[1]), 10)) * 2 +
                (Integer.parseInt(String.valueOf(icArray[2]), 10)) * 7 +
                (Integer.parseInt(String.valueOf(icArray[3]), 10)) * 6 +
                (Integer.parseInt(String.valueOf(icArray[4]), 10)) * 5 +
                (Integer.parseInt(String.valueOf(icArray[5]), 10)) * 4 +
                (Integer.parseInt(String.valueOf(icArray[6]), 10)) * 3 +
                (Integer.parseInt(String.valueOf(icArray[7]), 10)) * 2;

        //mod sum by 11
        int mod = sum % 11;

        char lastLetter = ' ';
        //setting last letter
        if(icArray[0] == 'S'){
            lastLetter = st[mod];
        }
        else{
            lastLetter = fg[mod];
        }

        //check last letter is the same or not
        if(icArray[8] != lastLetter){
            log.info("Actual last letter: {}", lastLetter);
            return false;
        }

        return true;
    }

    @Autowired
    UserRepository userRepository;

    public boolean duplicateCheck(String nric) {
        //if h2 data base have record, return false
        //else return true

        List<UserModel> userModelList = userRepository.findAll();

        userModelList.forEach(o -> log.info(o.getNric()));

        return userModelList.stream()
                .anyMatch(o -> o.getNric().equalsIgnoreCase(nric));
    }

//    //check phoneNumber, make sure have 8 or 9 in front and 8 digits in total
//    public boolean phoneNumberCheck(String number){
//        return number.chars().allMatch(Character::isDigit);
//    }
//
//    //check depositAmount, make sure it is digit only
//    public boolean depositAmountCheck(String amount){
//        return amount.matches("\\d+(\\.\\d+)?");  //match a number with optional decimal.
//    }
}
