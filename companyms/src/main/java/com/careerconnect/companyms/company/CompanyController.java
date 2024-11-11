package com.careerconnect.companyms.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company)
    {
        if(companyService.updateCompany(id,company))
        {
            return new ResponseEntity<>("Company Updated",HttpStatus.OK);
        }

        return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany(@RequestBody Company company)
    {
        if(companyService.deleteCompany(company))
        {
            return new ResponseEntity<>("Company Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        if(companyService.getCompanyById(id)!=null)
            return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
