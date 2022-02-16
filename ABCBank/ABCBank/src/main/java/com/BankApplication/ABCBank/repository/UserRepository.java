package com.BankApplication.ABCBank.repository;

import com.BankApplication.ABCBank.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    //DAO = repository
}
