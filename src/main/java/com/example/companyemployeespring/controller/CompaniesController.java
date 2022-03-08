package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompaniesController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companiesPage(ModelMap map){
        List<Company> companis = companyRepository.findAll();
        map.addAttribute("companis",companis);
        return "companies";
    }


    @GetMapping("/deleteCompanies/{id}")
    public String delete(@PathVariable("id") int id){
        companyRepository.deleteById(id);
        return "redirect:/companies";

    }

    @GetMapping("/addCompanies")
    public String addCompaniesPage(){
        return "saveCompanies";
    }

    @PostMapping("/addCompanies")
    public String addCompanies(@ModelAttribute Company company){
        companyRepository.save(company);
        return "redirect:/companies";
    }


}
