package de.eyuepekici.ats.repository;

import de.eyuepekici.ats.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByOrderByCreatedAtDesc();

    List<Application> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrderByCreatedAtDesc(
            String firstname,
            String lastname,
            String email
    );
}