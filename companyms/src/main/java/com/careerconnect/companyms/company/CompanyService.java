package com.careerconnect.companyms.company;

import java.util.List;


public interface CompanyService {

    List<Company> getAllCompanies();
    Boolean updateCompany(Long id,Company company);
    void createCompany(Company company);
    Boolean deleteCompany(Company company);
    Company getCompanyById(Long id);

}
