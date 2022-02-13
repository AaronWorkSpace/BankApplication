package com.BankApplication.ABCBank.repository;

import com.BankApplication.ABCBank.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user, Long> {
    //DAO = repository
}
