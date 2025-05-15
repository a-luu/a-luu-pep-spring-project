package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;
import com.example.SocialMediaApp;

import java.sql.*;
import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByUsernameAndPassword(String username, String password);
    
    Optional<Account> findAccountByUsername(String username);
}
