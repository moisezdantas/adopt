package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Breed;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long>{

    List<Breed> findByAnimalType(Long id);

    @Query("SELECT b FROM Breed b order by b.name asc")
    List<Breed> findAllOrderByName();
}
