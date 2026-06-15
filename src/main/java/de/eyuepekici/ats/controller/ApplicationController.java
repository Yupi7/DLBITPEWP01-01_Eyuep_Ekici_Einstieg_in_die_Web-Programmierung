package de.eyuepekici.ats.controller;

import de.eyuepekici.ats.entity.Application;
import de.eyuepekici.ats.entity.Job;
import de.eyuepekici.ats.repository.ApplicationRepository;
import de.eyuepekici.ats.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ApplicationController(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/apply/{jobId}")
    public String showApplicationForm(@PathVariable Long jobId, Model model) {
        Job job = jobRepository.findById(jobId).orElseThrow();

        model.addAttribute("job", job);
        model.addAttribute("application", new Application());

        return "apply";
    }

    @PostMapping("/apply/{jobId}")
    public String submitApplication(@PathVariable Long jobId,
                                    @ModelAttribute Application application) {
        Job job = jobRepository.findById(jobId).orElseThrow();

        application.setJob(job);
        application.setStatus("Eingegangen");
        application.setCreatedAt(LocalDateTime.now());

        applicationRepository.save(application);

        return "redirect:/application-success";
    }

    @GetMapping("/application-success")
    public String success() {
        return "application-success";
    }
}