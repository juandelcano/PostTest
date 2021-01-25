/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework1.controller;


import com.example.Framework1.models.Employee;
import com.example.Framework1.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cr7
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "employee";
    }
    
    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employee";
    }
    
    @PostMapping("/save")
    public String save(Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employee/";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        employeeRepository.delete(employee);
        return "redirect:/employee/";
    }
}
