package com.careerconnect.jobms.job;

import com.careerconnect.jobms.job.dto.JobDTO;
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
    ResponseEntity<List<JobDTO>> findAll()
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
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id)
    {
         JobDTO jobDTO = jobService.getJobById(id);
         if(jobDTO !=null)
              return new ResponseEntity<>(jobDTO, HttpStatus.OK);
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
