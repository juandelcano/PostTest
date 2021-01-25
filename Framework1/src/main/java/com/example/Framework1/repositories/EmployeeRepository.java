/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework1.repositories;

import com.example.Framework1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author cr7
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    
}
