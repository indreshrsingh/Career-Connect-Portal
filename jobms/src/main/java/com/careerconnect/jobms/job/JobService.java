package com.careerconnect.jobms.job;

import com.careerconnect.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id,Job job);
}
