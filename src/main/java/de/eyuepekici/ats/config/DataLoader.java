package de.eyuepekici.ats.config;

import de.eyuepekici.ats.entity.Job;
import de.eyuepekici.ats.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(JobRepository jobRepository) {

        return args -> {

            Job job1 = new Job();
            job1.setTitle("Systemadministrator");
            job1.setDescription("Administration von Windows-Systemen");
            job1.setLocation("Mannheim");
            job1.setCreatedAt(LocalDateTime.now());

            Job job2 = new Job();
            job2.setTitle("IT Support");
            job2.setDescription("Unterstützung von Anwendern");
            job2.setLocation("Heidelberg");
            job2.setCreatedAt(LocalDateTime.now());

            jobRepository.save(job1);
            jobRepository.save(job2);
        };
    }
}