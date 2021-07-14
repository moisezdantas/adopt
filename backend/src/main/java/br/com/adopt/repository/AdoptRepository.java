package br.com.adopt.repository;

import br.com.adopt.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptRepository extends JpaRepository<Adopter, Long> {
}
