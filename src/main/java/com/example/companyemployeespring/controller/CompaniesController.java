package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CompaniesController {

    private final CompanyService companyService;

    @GetMapping("/companies")
    public String companiesPage(ModelMap map){
        //List<Company> companis = companyService.findAll();
        map.addAttribute("companis",companyService.findAll());
        return "companies";
    }


    @GetMapping("/deleteCompanies/{id}")
    public String delete(@PathVariable("id") int id){
        companyService.deleteById(id);
        return "redirect:/companies";

    }

    @GetMapping("/addCompanies")
    public String addCompaniesPage(){
        return "saveCompanies";
    }

    @PostMapping("/addCompanies")
    public String addCompanies(@ModelAttribute Company company){
        companyService.save(company);
        return "redirect:/companies";
    }


}
