package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import com.example.companyemployeespring.service.CompanyService;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Value("${companyemployeesspring.upload.path}")
    private String imagePhat;

    @GetMapping("/employees")
    public String employeesPage(ModelMap map) {
        List<Employee> employees = employeeService.findAll();
        map.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/addEmployees")
    public String addEmployeesPage(ModelMap map) {
        List<Employee> employee = employeeService.findAll();
        map.addAttribute("employees", employee);
        map.addAttribute("company", companyService.findAll());
        return "saveEmployees";
    }


//    @PostMapping("/addEmployees")
//    public String addCompanies(@ModelAttribute Employee employee) {
//        employeeRepository.save(employee);
//        return "redirect:/employees";
//    }

    @PostMapping("/addEmployees")
    public String addEmployees(@ModelAttribute Employee employee, @RequestParam("picture") MultipartFile uploadedFile) throws IOException {
        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePhat + fileName);
            uploadedFile.transferTo(newFile);
            employee.setPicUrl(fileName);
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }


    @GetMapping("/employees/byCompany/{id}")
    public String employeesCompanyPage(ModelMap map, @PathVariable int id) {

        Company company = companyService.getById(id);
        List<Employee> employees = employeeService.findAllByCompany(company);
        map.addAttribute("employees", employees);
        return "employeesPage";
    }



}
