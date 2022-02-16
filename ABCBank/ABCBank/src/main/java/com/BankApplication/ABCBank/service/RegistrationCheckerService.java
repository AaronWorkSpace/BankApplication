package com.BankApplication.ABCBank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        //check if nric length is 9
        if(nric.length() != 9){
            return false;
        }
        nric = nric.toLowerCase();
        //check if first character is s / t / g
        char ch = nric.charAt(0);
        if(ch != 's' || ch != 't' || ch != 'g'){
            return false;
        }

        char digit;
        //check if there is 7 digits
        for(int i = 1; i < nric.length(); i++){
            digit = nric.charAt(i);
            if(!Character.isDigit(digit)){
                return false;
            }
        }

        int sum = 0;
        //multiply by weight 2, 7, 6, 5, 4, 3, 2
        for(int i = 1; i < nric.length(); i++){
            digit = nric.charAt(i);
            switch(i){
                case 2:
                    sum += i * 7;
                    break;
                case 3:
                    sum += i * 6;
                    break;
                case 4:
                    sum += i * 5;
                    break;
                case 5:
                    sum += i * 4;
                    break;
                case 6:
                    sum += i * 3;
                    break;
                default:
                    sum += i * 2;
                    break;
            }
        }

        //mod sum by 11
        int mod = sum % 11;

        char lastLetter = ' ';
        //setting last letter
        if(ch == 's'){
            switch(mod){
                case 0:
                    lastLetter = 'x';
                    break;
                case 1:
                    lastLetter = 'w';
                    break;
                case 2:
                    lastLetter = 'u';
                    break;
                case 3:
                    lastLetter = 't';
                    break;
                case 4:
                    lastLetter = 'r';
                    break;
                case 5:
                    lastLetter = 'q';
                    break;
                case 6:
                    lastLetter = 'p';
                    break;
                case 7:
                    lastLetter = 'n';
                    break;
                case 8:
                    lastLetter = 'm';
                    break;
                case 9:
                    lastLetter = 'l';
                    break;
                default:
                    lastLetter = 'k';
            }
        }
        else{
            switch(mod){
                case 0:
                    lastLetter = 'j';
                    break;
                case 1:
                    lastLetter = 'z';
                    break;
                case 2:
                    lastLetter = 'i';
                    break;
                case 3:
                    lastLetter = 'h';
                    break;
                case 4:
                    lastLetter = 'g';
                    break;
                case 5:
                    lastLetter = 'f';
                    break;
                case 6:
                    lastLetter = 'e';
                    break;
                case 7:
                    lastLetter = 'd';
                    break;
                case 8:
                    lastLetter = 'c';
                    break;
                case 9:
                    lastLetter = 'b';
                    break;
                default:
                    lastLetter = 'a';
            }
        }

        //check last letter is the same or not
        ch = nric.charAt(8);
        if(ch != lastLetter){
            log.info("Actual last letter: ()", lastLetter);
            return false;
        }

        return true;
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
