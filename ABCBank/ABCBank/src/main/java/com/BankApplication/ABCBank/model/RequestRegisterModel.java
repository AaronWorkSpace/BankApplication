package com.BankApplication.ABCBank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterModel {
    private String name;
//    private String email;
    private String NRIC;
    private String streetAddr;
    private String blockAddr;
    private String unitAddr;
    private String postalCodeAddr;
    private int phoneNumber;
    private long depositAmount;
}
