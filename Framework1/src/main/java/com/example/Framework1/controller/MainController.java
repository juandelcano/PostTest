/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework1.controller;

import com.example.Framework1.models.Department;
import com.example.Framework1.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cr7
 */

@Controller
@RequestMapping("department")
public class MainController {
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("departments",departmentRepository.findAll());
        model.addAttribute("department", new Department());
        return "index";
    }
    
    @PostMapping("/save")
    public String save(Department department){
        if(!department.getName().isEmpty() && !department.getId().isEmpty()){
            departmentRepository.save(department);
        }
        else {
            System.out.println("Data tidak boleh kosong");
        }
        return "redirect:/department/";
    }
    
    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("departments",departmentRepository.findAll());
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model){
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        departmentRepository.delete(department);
        return "redirect:/department/";
    }
    
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Department department, BindingResult result, Model model){
        if (result.hasErrors()){
            return "index";
        }
        departmentRepository.save(department);
        return "redirect:/department/";
    }
}
