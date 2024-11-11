package com.careerconnect.jobms.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    ResponseEntity<List<Job>> findAll()
    {
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
         jobService.createJob(job);
         return new ResponseEntity<>("Job Created",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id)
    {
         Job job=jobService.getJobById(id);
         if(job!=null)
              return new ResponseEntity<>(job, HttpStatus.OK);
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id)
    {
        if(jobService.deleteJobById(id))
            return new ResponseEntity<>("Job Deleted",HttpStatus.OK);
        return new ResponseEntity<>("Job Not Deleted",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job)
    {
         if(jobService.updateJobById(id,job))
             return new ResponseEntity<>("Job Updated",HttpStatus.OK);
         return new ResponseEntity<>("Job Not Found",HttpStatus.NOT_FOUND);
    }
}
