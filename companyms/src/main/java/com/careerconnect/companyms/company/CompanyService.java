package com.careerconnect.companyms.company;

import com.careerconnect.companyms.company.dto.ReviewMessage;

import java.util.List;


public interface CompanyService {

    List<Company> getAllCompanies();
    Boolean updateCompany(Long id,Company company);
    void createCompany(Company company);
    Boolean deleteCompany(Company company);
    Company getCompanyById(Long id);
    Boolean updateCompany(ReviewMessage reviewMessage);

}
