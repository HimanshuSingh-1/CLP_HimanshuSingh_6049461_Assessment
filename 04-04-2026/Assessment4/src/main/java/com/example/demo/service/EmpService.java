package com.example.demo.service;


import com.example.demo.entity.Emp;
import com.example.demo.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmpRepository repo;

    public List<Emp> getAllEmployees() {
        return repo.findAll();
    }

    public Emp getEmployeeById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void saveEmployee(Emp emp) {
        repo.save(emp);
    }

    public void deleteEmployee(Integer id) {
        repo.deleteById(id);
    }
}