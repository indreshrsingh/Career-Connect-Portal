package com.careerconnect.jobms.job.impl;


import com.careerconnect.jobms.job.Job;
import com.careerconnect.jobms.job.JobRepository;
import com.careerconnect.jobms.job.JobService;
import com.careerconnect.jobms.job.dto.JobWithCompanyDTO;
import com.careerconnect.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll()
    {
        List<Job> jobs=jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();
        RestTemplate restTemplate=new RestTemplate();
        for(Job job:jobs)
        {
            JobWithCompanyDTO jobWithCompanyDTO=new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company= restTemplate.getForObject("http://localhost:8081/comapanies/"+job.getCompanyId(),Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }

        return jobWithCompanyDTOs;
    }

    @Override
    public void createJob(Job job) {
           jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id)
    {
        return (Job) jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try
        {
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id,Job jobToUpdate) {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent())
        {
            Job job=jobOptional.get();
            job.setTitle(jobToUpdate.getTitle());
            job.setDescription(jobToUpdate.getDescription());
            job.setMinSalary(jobToUpdate.getMinSalary());
            job.setMaxSalary(jobToUpdate.getMaxSalary());
            job.setLocation(jobToUpdate.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
