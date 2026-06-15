package de.eyuepekici.ats.controller;

import de.eyuepekici.ats.entity.Job;
import de.eyuepekici.ats.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs";
    }

    @GetMapping("/jobs/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {
        Job job = jobRepository.findById(id)
                .orElseThrow();

        model.addAttribute("job", job);

        return "job-details";
    }
}