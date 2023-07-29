package com.java.dao;

import com.java.entities.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails,Integer> {

    List<EmployeeDetails> findByName(String name);
}
