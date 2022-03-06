package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employees")
    public String employeesPage(ModelMap map) {
        List<Employee> employees = employeeRepository.findAll();
        map.addAttribute("employees", employees);
        return "employees";
    }


//    @GetMapping("/addEmployees")
//    public String addEmployeesPage(){
//        return "saveEmployees";
//    }


    @GetMapping("/addEmployees")
    public String addEmployeesPage(ModelMap map) {
        map.addAttribute("company", companyRepository.findAll());
        return "saveEmployees";
    }

    @PostMapping("/addEmployees")
    public String addEmployees(ModelMap map,@ModelAttribute Employee employee) {
        map.addAttribute("company", companyRepository.findAll());
        return "saveEmployees";
    }


    
    @GetMapping("/employees/byCompany/{id}")
    public String employeesCompanyPage(ModelMap map, @PathVariable int id) {

        Company company = companyRepository.getById(id);
        List<Employee> empley = employeeRepository.findAllByCompany(company);
        map.addAttribute("empley", empley);
        return "employees";
    }
}
