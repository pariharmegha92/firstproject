package com.java.dao;

import com.java.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Integer> {
    List<UserDetails> findByUsername(String name);
}