package com.java.services;

import com.java.dao.EmployeeRepository;
import com.java.entities.EmployeeDetails;
import com.java.entities.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity addEmployee(String name, String mobile, String address) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setName(name);
        employeeDetails.setMobile(mobile);
        employeeDetails.setAddress(address);
        EmployeeDetails employee = employeeRepository.save(employeeDetails);
        if (employee != null) {
            return new ResponseEntity(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity("User not save in database!!..", HttpStatus.NO_CONTENT);
        }

    }

    public ResponseEntity updateEmployee(int id, String name, String address) {
        if (employeeRepository.existsById(id)) {
            EmployeeDetails employeeDetails = employeeRepository.findById(id).get();
            employeeDetails.setName(name);
            employeeDetails.setAddress(address);
            EmployeeDetails employee = employeeRepository.save(employeeDetails);
            return new ResponseEntity(employee, HttpStatus.OK);

        } else {
            return new ResponseEntity("Id doesn't exists!!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteemployee(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return new ResponseEntity("Successfully delete!!",HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity("Id doesn't exists!!",HttpStatus.NO_CONTENT);
        }
    }


    public ResponseEntity getAllEmployee() {
        List<EmployeeDetails> allEmployee = employeeRepository.findAll();
        return new ResponseEntity(allEmployee, HttpStatus.OK);
    }

    public ResponseEntity getEmployeeId(int empId) {
        if (employeeRepository.existsById(empId)) {
            EmployeeDetails employeeDetails = employeeRepository.findById(empId).get();
            return new ResponseEntity(employeeDetails, HttpStatus.OK);
        } else {
            System.out.println("user not available");
            return new ResponseEntity("id not found in databse", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity findByName(String name) {
        List<EmployeeDetails> employeeDetails = employeeRepository.findByName(name);
        if (!employeeDetails.isEmpty()) {
            return new ResponseEntity(employeeDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity("No data here...", HttpStatus.NO_CONTENT);
        }
    }

}
