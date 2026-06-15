package de.eyuepekici.ats.controller;

import de.eyuepekici.ats.entity.Application;
import de.eyuepekici.ats.repository.ApplicationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final ApplicationRepository applicationRepository;

    public AdminController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/admin/applications")
    public String applications(Model model) {
        model.addAttribute("applications", applicationRepository.findAll());
        return "admin-applications";
    }

    @PostMapping("/admin/applications/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam String status) {

        Application application = applicationRepository.findById(id)
                .orElseThrow();

        application.setStatus(status);
        applicationRepository.save(application);

        return "redirect:/admin/applications";
    }

    @PostMapping("/admin/applications/{id}/note")
    public String updateNote(@PathVariable Long id,
                             @RequestParam String note) {

        Application application = applicationRepository.findById(id)
                .orElseThrow();

        application.setNote(note);
        applicationRepository.save(application);

        return "redirect:/admin/applications";
    }
}