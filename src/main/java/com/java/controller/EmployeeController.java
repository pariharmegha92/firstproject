package com.java.controller;

import ch.qos.logback.core.util.COWArrayList;
import com.java.entities.EmployeeDetails;
import com.java.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity addEmployeeDetails(@RequestParam(name = "name") String name, @RequestParam(name = "mobile") String mobile, @RequestParam(name = "address") String address) {
        if (name.isEmpty()) {
            return new ResponseEntity("please give name", HttpStatus.NOT_ACCEPTABLE);
        } else {
            ResponseEntity responseEntity = employeeService.addEmployee(name, mobile, address);
            return responseEntity;
        }
    }


    @PostMapping("/update-employee")
    public ResponseEntity updateemployee(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name, @RequestParam(name = "address") String address) {
        if (id <= 0) {
            return new ResponseEntity("Please give proper id!!", HttpStatus.NOT_ACCEPTABLE);
        }
        return employeeService.updateEmployee(id, name, address);
    }


    @PostMapping("/delete-employee")
    public ResponseEntity deleteEmployee(@RequestParam(name = "empId") int empId) {

        employeeService.deleteemployee(empId);
        return new ResponseEntity("id deleted by the user", HttpStatus.OK);

    }


    @GetMapping("/allEmployee")
    public ResponseEntity listOfAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getEmployeeById")
    public ResponseEntity getEmployeeById(@RequestParam(name = "empId") int id) {
        if (id <= 0) {
            return new ResponseEntity("please give proper id", HttpStatus.OK);
        } else {
            System.out.println("given id is not in databse");
            return employeeService.getEmployeeId(id);
        }
    }

    @GetMapping("/getByEmpName")
    public ResponseEntity getByEmpName(@RequestParam(name = "username") String name) {
        if(!name.isEmpty()) {
            return employeeService.findByName(name);
        }else{
            return new ResponseEntity("first give name",HttpStatus.OK);
        }
    }
}
