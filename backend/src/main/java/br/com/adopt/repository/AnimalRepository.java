package br.com.adopt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

    @Query("SELECT a from Animal a inner join a.breed b where b.animalType.id=:id and a.adopter is null")
    Page<Animal> findAllByAnimalType(@Param(value = "id") Long id,Pageable pageable);

    @Query("SELECT a from Animal a where a.adopter is null")
    Page<Animal> findAllAdopterIsNull(Pageable pageable);
}
