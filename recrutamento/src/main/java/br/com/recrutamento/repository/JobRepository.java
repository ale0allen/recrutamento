package br.com.recrutamento.repository;

import br.com.recrutamento.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
