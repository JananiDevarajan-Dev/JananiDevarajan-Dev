package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;


public interface DepartmentService {
	// Save operation
    Department saveDepartment(Department department);
 
    // Read operation
    List<Department> fetchDepartmentList();
 
    // Update operation
    Department updateDepartment(Department department,
                                Long departmentId);
 
    // Delete operation
    void deleteDepartmentById(Long departmentId);

}
