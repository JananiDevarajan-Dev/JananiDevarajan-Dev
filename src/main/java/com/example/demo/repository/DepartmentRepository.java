package com.example.demo.repository;
//Importing required classes
import  com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department, Long>  {

}
