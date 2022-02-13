package com.BankApplication.ABCBank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    private String NRIC;
    private String streetAddr;
    private String blockAddr;
    private String unitAddr;
    private String postalCodeAddr;
    private int phoneNumber;
    private long depositAmount;
}
