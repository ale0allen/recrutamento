package br.com.recrutamento.service;

import br.com.recrutamento.model.Job;
import br.com.recrutamento.model.JobApplication;
import br.com.recrutamento.model.AppUser;
import br.com.recrutamento.repository.JobApplicationRepository;
import br.com.recrutamento.repository.JobRepository;
import br.com.recrutamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public void applyForJob(Long jobId, String username) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            JobApplication jobApplication = new JobApplication();
            jobApplication.setJob(job);
            jobApplication.setUser(user);
            jobApplicationRepository.save(jobApplication);
            System.out.println("Usuário " + user.getUsername() + " candidatou-se à vaga " + job.getTitle());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
