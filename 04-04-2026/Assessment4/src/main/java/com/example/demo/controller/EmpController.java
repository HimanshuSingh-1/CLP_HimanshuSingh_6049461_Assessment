package com.example.demo.controller;

import com.example.demo.dto.EmpDto;
import com.example.demo.entity.Emp;
import com.example.demo.service.EmpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    // View all employees
//    @GetMapping("/viewall")
//    public String viewAll(Model model) {
//        List<Emp> empList = service.getAllEmployees();
//        model.addAttribute("employees", empList);
//        return "viewall";
//    }
//    
    @GetMapping("/viewall")
    public String viewAll(Model model,
                          @RequestParam(value="msg", required=false) String msg) {
        List<Emp> empList = service.getAllEmployees();
        model.addAttribute("employees", empList);
        model.addAttribute("message", msg);
        return "viewall";
    }

    // Edit employee
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Integer id, Model model) {
        Emp emp = service.getEmployeeById(id);

        EmpDto dto = new EmpDto();
        dto.setEmpId(emp.getEmpId());
        dto.setEmpName(emp.getEmpName());
        dto.setEmpSal(emp.getEmpSal());
        dto.setEmpDoj(emp.getEmpDoj());
        dto.setDeptName(emp.getDeptName());

        model.addAttribute("emp", dto);
        return "edit";
    }

    // Save edited employee
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("emp") EmpDto dto,
                               BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            return "edit";
        }

        Emp emp = new Emp();
        emp.setEmpId(dto.getEmpId());
        emp.setEmpName(dto.getEmpName());
        emp.setEmpSal(dto.getEmpSal());
        emp.setEmpDoj(dto.getEmpDoj());
        emp.setDeptName(dto.getDeptName());

        service.saveEmployee(emp);

       // return "redirect:/viewall";
        
        return "redirect:/viewall?msg=Employee Edited";
    }

    // Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        service.deleteEmployee(id);
      //  return "redirect:/viewall";
        
        return "redirect:/viewall?msg=Employee Deleted";
    }
}