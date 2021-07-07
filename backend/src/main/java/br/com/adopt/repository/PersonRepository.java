package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    @Query("SELECT p from Person p where p.user.id =:id")
    Optional<Person> findPersonByUserId(@Param("id") Long id);

}
