package de.eyuepekici.ats.repository;

import de.eyuepekici.ats.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}