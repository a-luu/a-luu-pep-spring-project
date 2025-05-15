package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;
import com.example.SocialMediaApp;

import java.sql.*;
import java.util.*;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Optional<List<Message>> findMessagesByPostedBy(Integer postedBy);

    
}
