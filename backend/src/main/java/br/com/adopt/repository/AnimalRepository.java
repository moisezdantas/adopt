package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{ 

}
