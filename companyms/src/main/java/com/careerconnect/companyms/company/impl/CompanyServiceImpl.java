package com.careerconnect.companyms.company.impl;


import com.careerconnect.companyms.company.Company;
import com.careerconnect.companyms.company.CompanyRepository;
import com.careerconnect.companyms.company.CompanyService;
import com.careerconnect.companyms.company.clients.ReviewClient;
import com.careerconnect.companyms.company.dto.ReviewMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;
    ReviewClient reviewClient;
    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Boolean updateCompany(Long id,Company updatedCompany) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()) {
            Company company=companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            //company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;

    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        return companyOptional.orElse(null);
    }

    @Override
    public Boolean updateCompany(ReviewMessage reviewMessage) {
        Optional<Company> companyOptional=companyRepository.findById(reviewMessage.getCompanyId());
        if(companyOptional.isPresent()) {
               Company company=companyOptional.get();
               Double rating=reviewClient.getAverageRatingforCompany(company.getId());
               company.setRating(rating);
               companyRepository.save(company);
               return true;
        }
        return false;
    }

    @Override
    public void  createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Boolean deleteCompany(Company company) {
        Optional<Company> optionalCompany=companyRepository.findById(company.getId());
        if(optionalCompany.isPresent()) {
            companyRepository.delete(optionalCompany.get());
            return true;
        }
        return false;
    }
}
