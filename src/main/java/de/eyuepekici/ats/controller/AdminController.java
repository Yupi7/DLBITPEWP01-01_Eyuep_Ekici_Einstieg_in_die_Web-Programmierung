package de.eyuepekici.ats.controller;

import de.eyuepekici.ats.entity.Application;
import de.eyuepekici.ats.repository.ApplicationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final ApplicationRepository applicationRepository;

    public AdminController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/admin/applications")
    public String applications(@RequestParam(required = false) String search,
                               Model model) {

        List<Application> applications;

        if (search != null && !search.isBlank()) {
            applications = applicationRepository
                    .findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrderByCreatedAtDesc(
                            search,
                            search,
                            search
                    );
        } else {
            applications = applicationRepository.findAllByOrderByCreatedAtDesc();
        }

        List<Application> allApplications = applicationRepository.findAll();

        long total = allApplications.size();

        long eingegangen = allApplications.stream()
                .filter(a -> "Eingegangen".equals(a.getStatus()))
                .count();

        long pruefung = allApplications.stream()
                .filter(a -> "In Prüfung".equals(a.getStatus()))
                .count();

        long interview = allApplications.stream()
                .filter(a -> "Interview".equals(a.getStatus()))
                .count();

        long angenommen = allApplications.stream()
                .filter(a -> "Angenommen".equals(a.getStatus()))
                .count();

        long abgelehnt = allApplications.stream()
                .filter(a -> "Abgelehnt".equals(a.getStatus()))
                .count();

        model.addAttribute("applications", applications);
        model.addAttribute("search", search);

        model.addAttribute("total", total);
        model.addAttribute("eingegangen", eingegangen);
        model.addAttribute("pruefung", pruefung);
        model.addAttribute("interview", interview);
        model.addAttribute("angenommen", angenommen);
        model.addAttribute("abgelehnt", abgelehnt);

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