package com.careerconnect.jobms.job.impl;


import com.careerconnect.jobms.job.Job;
import com.careerconnect.jobms.job.JobRepository;
import com.careerconnect.jobms.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
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
