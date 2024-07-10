package br.com.recrutamento.controller;

import br.com.recrutamento.model.Job;
import br.com.recrutamento.model.AppUser;
import br.com.recrutamento.service.JobService;
import br.com.recrutamento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job, Principal principal) {
        AppUser user = userService.findByUsername(principal.getName());
        job.setUser(user);
        Job savedJob = jobService.saveJob(job);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping("/apply")
    public ResponseEntity<Void> applyForJob(@RequestParam Long jobId, Principal principal) {
        jobService.applyForJob(jobId, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

